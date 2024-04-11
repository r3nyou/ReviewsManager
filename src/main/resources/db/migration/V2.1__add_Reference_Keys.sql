ALTER TABLE customerReviews ADD FOREIGN KEY (merchantId) REFERENCES  merchants(id);

ALTER TABLE hotkeywords ADD FOREIGN KEY (merchantId) REFERENCES merchants(id);