package com.market.fishmarket.infostructure.adapter.in.controller;

import com.market.fishmarket.application.port.in.ManageFishUseCase;
import com.market.fishmarket.infostructure.adapter.in.dto.FishDTO;
import com.market.fishmarket.infostructure.adapter.in.mapper.FishMapper;
import com.market.fishmarket.infostructure.adapter.in.mapper.WebFishMapper;
import com.market.fishmarket.infostructure.adapter.in.validator.FishDtoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/fish")
@RequiredArgsConstructor
public class FishController {

    private final ManageFishUseCase fishUseCase;
    private final FishDtoValidator validator;
    private final WebFishMapper webFishMapper;

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        FishDTO fishDTO = FishMapper.toFishDTO(fishUseCase.getNewFish());
        model.addAttribute("fish", fishDTO);
        return "createFish";
    }

    @PostMapping("/create")
    public String addFish(@Validated @ModelAttribute("fish") FishDTO fishDTO,
                          BindingResult bindingResult,
                          Model model) {

        validator.validate(fishDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("fish", fishDTO);
            return "createFish";
        }

        List<String> savedFileNames = webFishMapper.mapMultipartFilesToFileNames(fishDTO.getImageFiles());

        fishUseCase.saveFishInDb(fishDTO.getName(), fishDTO.getPrice(), savedFileNames);

        return "redirect:/fish";
    }

    @GetMapping("/delete")
    public String deleteFish(@RequestParam int id) {
        fishUseCase.deleteFish(id);
        return "redirect:/fish";
    }

    @GetMapping({"", "/"})
    public String showFishList(Model model) {
        model.addAttribute("fishlist", fishUseCase.getSortedFishList());
        return "index";
    }

}
