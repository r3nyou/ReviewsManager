

ALTER TABLE customerReviews
DROP FOREIGN KEY customerReviews_ibfk_1;

ALTER TABLE hotkeywords
DROP FOREIGN KEY hotkeywords_ibfk_1;

ALTER TABLE merchants
    MODIFY COLUMN id INT AUTO_INCREMENT;
