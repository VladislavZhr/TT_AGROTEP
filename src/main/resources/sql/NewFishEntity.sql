
ALTER TABLE fish
MODIFY COLUMN price DECIMAL(10,2) NOT NULL;

CREATE TABLE fish_images (
    fish_id INT NOT NULL,
    image_file_name VARCHAR(255) NOT NULL,
    FOREIGN KEY (fish_id) REFERENCES fish(id)
);

INSERT INTO fish_images (fish_id, image_file_name)
SELECT id, image_file_name
FROM fish
WHERE image_file_name IS NOT NULL;

ALTER TABLE fish
DROP COLUMN image_file_name;
