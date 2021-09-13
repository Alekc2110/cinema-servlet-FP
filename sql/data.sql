INSERT INTO `hall` VALUES (1,'50','Multiplex movie theater');

INSERT INTO `row` VALUES (1),
                         (2),
                         (3),
                         (4),
                         (5);

INSERT INTO `seat` VALUES(1,1,1),
                         (2,1,2),
                         (3,1,3),
                         (4,1,4),
                         (5,1,5),
                         (6,1,6),
                         (7,1,7),
                         (8,1,8),
                         (9,1,9),
                         (10,1,10);
INSERT INTO `seat` VALUES(21,2,1),
                         (22,2,2),
                         (23,2,3),
                         (24,2,4),
                         (25,2,5),
                         (26,2,6),
                         (27,2,7),
                         (28,2,8),
                         (29,2,9),
                         (30,2,10);
INSERT INTO `seat` VALUES(41,3,1),
                         (42,3,2),
                         (43,3,3),
                         (44,3,4),
                         (45,3,5),
                         (46,3,6),
                         (47,3,7),
                         (48,3,8),
                         (49,3,9),
                         (50,3,10);

INSERT INTO `user` VALUES (1,'Max','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','max@gmail.com'),
                          (2,'John','bb0cf6eb9b17d0f7d22b456f121257dc1254e1f01665370476383ea776df414','john@gmail.com'),
                          (3,'Admin1','25f43b1486ad95a1398e3eeb3d83bc4010015fcc9bedb35b432e00298d5021f7','admin1@gmail.com'),
                          (4,'Admin2','1c142b2d01aa34e9a36bde480645a57fd69e14155dacfab5a3f9257b77fdc8d8','admin2@gmail.com');

INSERT INTO `shopping_cart` VALUES (1,1),
                                   (2,2),
                                   (3,3),
                                   (4,4);

INSERT INTO `role` VALUES (1,'ADMIN'),
                          (2,'USER');

INSERT INTO `user_role` VALUES (1,2),
                               (2,2),
                               (3,1),
                               (4,1);

INSERT INTO `movie` VALUES (1,'THE SUICIDE SQUAD','There is one rotten place on earth, from where even the most notorious villains dream of dumping. Belle Reeve Prison is for superpowered criminals. She''s hell. It is also the recruitment base for the super-secret project "Suicide Squad".', 'https://zupimages.net/up/21/36/dqo1.jpeg', 'James Gunn', 'USA', '2021'),
                           (2,'SHANG CHI AND THE LEGEND OF THE TEN RINGS','Master of the martial arts Shang-Chi must confront ghosts from his own past as he is pulled into the web of intrigue by the mysterious Ten Rings organization.', 'https://zupimages.net/up/21/36/zl4e.jpg', 'Destin Daniel Cretton', 'USA', '2021'),
                           (3,'FREE GUY','An adventure comedy about a lonely bank teller who discovers that he is in fact a background character in the Free City video game. Will he have the courage to rewrite his code, finally turn his attention to the beautiful girl and save the world?', 'https://zupimages.net/up/21/36/h2cl.jpg', 'Shawn Levy', 'USA', '2021'),
                           (4,'CANDYMAN','There is a legend about the mysterious Candyman - a ghost from a parallel world. But one has only to stand in front of the mirror and say his name five times in a row, the darkness will cross the border. The macabre sequel brings a fearsome urban legend back to the present day.', 'https://zupimages.net/up/21/36/dc7p.jpg', 'Nia DaCosta', 'USA', '2021' );

INSERT INTO `movie_session` VALUES (1,1,'2021-09-10 10:00',100),
                                   (2,1,'2021-09-10 14:00',100),
                                   (3,1,'2021-09-10 18:00',120),
                                   (4,1,'2021-09-10 21:00',150),
                                   (5,1,'2021-09-11 10:00',100),
                                   (6,1,'2021-09-11 14:00',100),
                                   (7,1,'2021-09-11 18:00',120),
                                   (8,1,'2021-09-11 21:00',150),
                                   (9,1,'2021-09-12 10:00',100),
                                   (10,1,'2021-09-12 14:00',100),
                                   (11,1,'2021-09-12 18:00',120),
                                   (12,1,'2021-09-12 21:00',150),
                                   (13,2,'2021-09-10 14:00',50),
                                   (14,2,'2021-09-10 18:00',100),
                                   (15,2,'2021-09-10 21:00',100),
                                   (16,2,'2021-09-11 14:00',50),
                                   (17,2,'2021-09-11 18:00',100),
                                   (18,2,'2021-09-11 21:00',100),
                                   (19,2,'2021-09-12 14:00',50),
                                   (20,2,'2021-09-12 18:00',100),
                                   (21,2,'2021-09-12 21:00',100),
                                   (22,3,'2021-09-10 18:00',150),
                                   (23,3,'2021-09-10 21:00',150),
                                   (24,3,'2021-09-12 18:00',150),
                                   (25,3,'2021-09-12 21:00',150),
                                   (26,4,'2021-10-12 18:00',150),
                                   (27,4,'2021-09-25 18:00',150);

INSERT INTO `order` VALUES (1,'2021-09-08 10:00',1),
                           (2,'2021-09-09 20:00',1),
                           (3,'2021-09-05 20:30',2),
                           (4,'2021-09-05 12:00',1),
                           (5,'2021-09-05 20:30',2);

INSERT INTO `ticket` VALUES (1,1,1,1,10,1),
                            (2,1,10,2,25,2),
                            (3,2,3,1,20,3),
                            (4,2,5,1,10,3),
                            (5,2,5,1,11,3),
                            (6,2,15,1,5,4),
                            (7,1,20,1,1,5),
                            (8,1,20,1,2,5),
                            (9,1,20,1,3,5);
