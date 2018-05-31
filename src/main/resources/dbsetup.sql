create table if not exists Category (
  id varchar(64) not null,
  name varchar(64) not null,
  description varchar(512),
  primary key (id)
);

create table if not exists Actor (
  id varchar(64) not null,
  firstName varchar(64) not null,
  lastName varchar(64) not null,
  primary key (id)
);

create table if not exists Director (
  id varchar(64) not null,
  firstName varchar(64) not null,
  lastName varchar(64) not null,
  primary key (id)
);

create table if not exists Movie (
  id varchar(64) not null,
  name varchar(64) not null,
  categoryId varchar(64),
  duration int,
  releaseYear int,
  description varchar(512),
  directorId varchar(64),
  primary key (id)
);

create table if not exists ActorMovie (
  actorId varchar(64) not null,
  movieId varchar(64) not null,
  name varchar(64) not null,
  primary key (actorId, movieId)
);

insert into Category (id, name, description) values
  ('2eebc638-6c17-45f2-8d75-94edef32b536', 'Marvel Cinematic Universe', 'Movies taking place in the Marvel Cinematic Universe');

insert into Director (id, firstName, lastName) values
  ('ae03ee2e-4767-4812-8d57-dc22aed4d8d5', 'Jon', 'Favreau'),
  ('f71fb8c5-3f0f-4917-bfd0-38b1e0b7115e', 'Louis', 'Leterrier'),
  ('2a17e215-8422-45e9-93b2-1edbcda440b6', 'Kenneth', 'Branagh'),
  ('0e380cc0-ae4c-4135-a4b1-b6ac911a1310', 'Joe', 'Johnston'),
  ('9431209f-290a-4312-943b-8c7a6d937dc6', 'Joss', 'Whedon');

insert into Actor (id, firstName, lastName) values
  ('a7270fc9-0912-452c-9dfc-6a45fb478ee3', 'Robert', 'Downey Jr'),
  ('a7d20b23-b587-45d7-a79f-5b5873565ea0', 'Chris', 'Evans'),
  ('5ddadf9e-7d54-4ffb-a1fd-79328fbb6d15', 'Edward', 'Norton'),
  ('49ae50ec-ae19-4224-bdaf-afc920470fd9', 'Mark', 'Ruffalo'),
  ('9640bb06-b6f6-40ed-a642-c82ee1df480e', 'Chris', 'Hemsworth'),
  ('a7a1b1fd-04c4-4edd-94b1-3b935ec2167e', 'Scarlett', 'Johansson'),
  ('d9aff2f1-ad48-4f25-946d-b2408b4d9c62', 'Samuel L.', 'Jackson');

insert into Movie (id, name, categoryId, duration, releaseYear, directorId, description) values
  ('c856f9df-449b-4bcb-a928-f346efdfaa3e', 'Iron Man', '2eebc638-6c17-45f2-8d75-94edef32b536', 7560, 2008, 'ae03ee2e-4767-4812-8d57-dc22aed4d8d5', 'Genius, billionaire, and playboy Tony Stark, who has inherited the defense contractor Stark Industries from his father...'),
  ('6c75e160-15df-4ee1-96ab-aab926557d01', 'The Incredible Hulk', '2eebc638-6c17-45f2-8d75-94edef32b536', 6720, 2008, 'f71fb8c5-3f0f-4917-bfd0-38b1e0b7115e', 'At Culver University in Virginia, General Thunderbolt Ross meets with Dr. Bruce Banner, the colleague and boyfriend of his daughter Betty, regarding an experiment that Ross claims...'),
  ('5aec35d2-9c57-49b6-96c7-8c382515e74b', 'Iron Man 2', '2eebc638-6c17-45f2-8d75-94edef32b536', 7500, 2010, 'ae03ee2e-4767-4812-8d57-dc22aed4d8d5', 'In Russia, the media covers Tony Stark''s disclosure of his identity as Iron Man. Ivan Vanko, whose father Anton Vanko has just died, sees this and begins building a miniature arc reactor similar to Stark...'),
  ('7ad2c00b-5edd-4ddf-8a49-4c3f5631d820', 'Thor', '2eebc638-6c17-45f2-8d75-94edef32b536', 6840, 2011, '2a17e215-8422-45e9-93b2-1edbcda440b6', 'In 965 AD, Odin, king of Asgard, wages war against the Frost Giants of Jotunheim and their leader Laufey, to prevent them from conquering the nine realms, starting with Earth...'),
  ('8d5d1b6d-b11c-43fe-87e9-31b156d1b1f9', 'Captain America: The First Avenger', '2eebc638-6c17-45f2-8d75-94edef32b536', 7440, 2011, '0e380cc0-ae4c-4135-a4b1-b6ac911a1310', 'In the present day, scientists in the Arctic uncover an old, frozen aircraft. In March 1942, Nazi officer Johann Schmidt and his men steal a mysterious relic called the Tesseract...'),
  ('a6d00d4c-9545-46a1-94f3-f58255e8f737', 'Marvel''s The Avengers', '2eebc638-6c17-45f2-8d75-94edef32b536', 8580, 2012, '9431209f-290a-4312-943b-8c7a6d937dc6', 'The Asgardian Loki encounters the Other, the leader of an extraterrestrial race known as the Chitauri. In exchange for retrieving the Tesseract...');

insert into ActorMovie (actorId, movieId, name) values
  ('a7270fc9-0912-452c-9dfc-6a45fb478ee3', 'c856f9df-449b-4bcb-a928-f346efdfaa3e', 'Iron Man'),
  ('5ddadf9e-7d54-4ffb-a1fd-79328fbb6d15', '6c75e160-15df-4ee1-96ab-aab926557d01', 'Bruce Banner / Hulk'),
  ('a7270fc9-0912-452c-9dfc-6a45fb478ee3', '5aec35d2-9c57-49b6-96c7-8c382515e74b', 'Iron Man'),
  ('a7a1b1fd-04c4-4edd-94b1-3b935ec2167e', '5aec35d2-9c57-49b6-96c7-8c382515e74b', 'Natasha Romanoff / Black Widow'),
  ('d9aff2f1-ad48-4f25-946d-b2408b4d9c62', '5aec35d2-9c57-49b6-96c7-8c382515e74b', 'Nick Fury'),
  ('9640bb06-b6f6-40ed-a642-c82ee1df480e', '7ad2c00b-5edd-4ddf-8a49-4c3f5631d820', 'Thor'),
  ('a7d20b23-b587-45d7-a79f-5b5873565ea0', '8d5d1b6d-b11c-43fe-87e9-31b156d1b1f9', 'Steve Rogers / Captain America'),
  ('a7270fc9-0912-452c-9dfc-6a45fb478ee3', 'a6d00d4c-9545-46a1-94f3-f58255e8f737', 'Iron Man'),
  ('a7d20b23-b587-45d7-a79f-5b5873565ea0', 'a6d00d4c-9545-46a1-94f3-f58255e8f737', 'Steve Rogers / Captain America'),
  ('49ae50ec-ae19-4224-bdaf-afc920470fd9', 'a6d00d4c-9545-46a1-94f3-f58255e8f737', 'Bruce Banner / Hulk'),
  ('9640bb06-b6f6-40ed-a642-c82ee1df480e', 'a6d00d4c-9545-46a1-94f3-f58255e8f737', 'Thor'),
  ('a7a1b1fd-04c4-4edd-94b1-3b935ec2167e', 'a6d00d4c-9545-46a1-94f3-f58255e8f737', 'Natasha Romanoff / Black Widow'),
  ('d9aff2f1-ad48-4f25-946d-b2408b4d9c62', 'a6d00d4c-9545-46a1-94f3-f58255e8f737', 'Nick Fury');
