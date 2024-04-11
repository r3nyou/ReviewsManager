CREATE TABLE MerchantList (
                              PlaceID VARCHAR(36) PRIMARY KEY,
                              accessibilityOptions INT,
                              businessStatus BOOLEAN,
                              displayName VARCHAR(255),
                              googleMapsUri VARCHAR(255),
                              utcOffsetMinutes VARCHAR(255),
                              currentOpeningHours DATETIME,
                              currentSecondaryOpeningHours DATETIME,
                              internationalPhoneNumber VARCHAR(255),
                              nationalPhoneNumber VARCHAR(255),
                              priceLevel VARCHAR(255),
                              rating VARCHAR(255),
                              regularOpeningHours DATETIME,
                              regularSecondaryOpeningHours DATETIME,
                              userRatingCount INT,
                              websiteUri VARCHAR(255)
);