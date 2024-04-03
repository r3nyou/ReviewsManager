CREATE TABLE merchants (
  id INTEGER NOT NULL PRIMARY KEY,
  placeId VARCHAR(64),
  displayName VARCHAR(128),
  rating FLOAT,
  userRatingCount INT,
  googleMapsUri VARCHAR(128),
  websiteUri VARCHAR(128),
  createAt DATETIME,
  updateAt DATETIME
);

CREATE TABLE customerReviews (
  id INTEGER NOT NULL PRIMARY KEY,
  merchantId INTEGER NOT NULL,
  rating INT,
  text TEXT,
  publishTime DATETIME,
  createAt DATETIME,
  updateAt DATETIME
);

CREATE TABLE hotkeywords (
  id INTEGER NOT NULL PRIMARY KEY,
  merchantId INTEGER,
  keyword VARCHAR(256),
  count INT,
  reviewType INT,
  createAt DATETIME,
  updateAt DATETIME
);

ALTER TABLE customerReviews ADD FOREIGN KEY (merchantId) REFERENCES  merchants(id);

ALTER TABLE hotkeywords ADD FOREIGN KEY (merchantId) REFERENCES merchants(id);