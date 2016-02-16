CREATE TABLE Attachments (id  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, tweets_id integer(10) NOT NULL, url text NOT NULL, description text, FOREIGN KEY(tweets_id) REFERENCES Tweets(id));
CREATE TABLE FavoriteUsers (id INTEGER NOT NULL PRIMARY KEY, group_color smallint(5) DEFAULT 0 NOT NULL, rating real(10) DEFAULT -1 NOT NULL, CONSTRAINT replace UNIQUE (id), FOREIGN KEY(id) REFERENCES Users(id));
CREATE TABLE Tweets (id  INTEGER NOT NULL PRIMARY KEY, author text NOT NULL, content text NOT NULL);
CREATE TABLE Users (id  INTEGER NOT NULL PRIMARY KEY, nick text NOT NULL);

