DROP TABLE IF EXISTS carts, products_in_categories, products, users, categories, staff;

CREATE TABLE `staff` (
  `id` mediumint(8) unsigned NOT NULL auto_increment,
  `firstname` varchar(255) default NULL,
  `surname` varchar(255) default NULL,
  `dob` varchar(255),
  `street_address` varchar(255) default NULL,
  `town` varchar(255),
  `postcode` varchar(10) default NULL,
  `mobile` varchar(100) default NULL,
  `email` varchar(255) default NULL,
  `salary` mediumint default NULL,
	UNIQUE(email),
	PRIMARY KEY (`id`)
) AUTO_INCREMENT=1;

CREATE TABLE products
(
	id mediumint(8) unsigned NOT NULL auto_increment,
	name varchar(100) NOT NULL,
	description varchar(1000) NOT NULL,
	cost decimal(12, 2) NOT NULL,
	rrp decimal(12, 2) NOT NULL,
	PRIMARY KEY(id)
) AUTO_INCREMENT=1;

CREATE TABLE users 
(
	id mediumint(8) unsigned NOT NULL auto_increment,
	email varchar(100) NOT NULL,
	password varchar(50) NOT NULL,
	firstname varchar(100) NOT NULL,
	surname varchar(100) NOT NULL,
	street_address varchar(100) NOT NULL,
	postcode varchar(10) NOT NULL,
	town varchar(100) NOT NULL,
	phonenumber varchar(20) default NULL,
	UNIQUE(email),
	INDEX(email),
	PRIMARY KEY(id)
) AUTO_INCREMENT=1;

CREATE TABLE carts
(
	user_id mediumint(8) unsigned NOT NULL,
	product_id mediumint(8) unsigned NOT NULL, 
	quantity mediumint(6) unsigned NOT NULL,
	CONSTRAINT FK_CARTS_USERID FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
	CONSTRAINT FK_CARTS_PRODUCTID FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
	PRIMARY KEY(user_id, product_id)
);

CREATE TABLE categories 
(
	id mediumint(8) unsigned NOT NULL auto_increment,
	name varchar(100) NOT NULL,
	staff_responsible mediumint(8) unsigned NOT NULL,
	CONSTRAINT FK_CATEGORIES_STAFF_RESPONSIBLE FOREIGN KEY(staff_responsible) REFERENCES staff(id),
	PRIMARY KEY(id)
) AUTO_INCREMENT=1;

CREATE TABLE products_in_categories
(
	product_id mediumint(8) unsigned NOT NULL,
	category_id mediumint(8) unsigned NOT NULL,
	CONSTRAINT FK_PRODINCAT_PRODID FOREIGN KEY(product_id) REFERENCES products(id) ON DELETE CASCADE,
	CONSTRAINT FK_PRODINCAT_CATID FOREIGN KEY(category_id) REFERENCES categories(id) ON DELETE CASCADE,
	PRIMARY KEY(product_id, category_id)
);

INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Sybill","Le","1973-05-11","P.O. Box 173, 4391 Felis Ave","Redcliffe","QM3 9VI","0800 160 9672","elit.erat.vitae@nonnisi.org",35805);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Kalia","Horn","1982-08-27","708-9236 Ac St.","Piancastagnaio","UR6 4PF","(013856) 08737","Duis@lobortisquam.co.uk",39782);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Tatum","Bailey","1956-07-17","2295 Inceptos Av.","Morrovalle","EN6 6DH","(0151) 475 0217","mollis.lectus.pede@quismassa.com",36747);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("India","Franco","1982-02-15","Ap #499-6083 Vitae, Ave","Virginia Beach","L99 2YC","07624 649116","velit.Pellentesque.ultricies@nequeseddictum.org",22734);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Sophia","Nash","1957-12-26","Ap #986-9837 Convallis Street","Maria","VA67 3CS","070 3590 4600","ut@arcu.com",51154);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Hillary","Brennan","1984-12-15","Ap #556-7189 Vestibulum. Ave","Borgone Susa","QP3O 5HT","055 3532 1835","metus@auctorquistristique.com",59563);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Juliet","Crane","1979-05-05","224-3688 Augue St.","Tolve","T1O 8EL","(01223) 29551","nisl.arcu@eliteratvitae.org",30010);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Ian","Melendez","1971-04-03","Ap #621-3246 Cursus Rd.","Weert","MO2L 7JU","0800 629 1936","ut@enim.net",24560);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Fritz","Holder","1967-10-21","558-600 Eu Av.","Gressan","P3 5KQ","070 3402 7173","lacus.varius@Curabiturdictum.co.uk",26688);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Eve","White","1969-06-23","579 Suspendisse Av.","Alphen aan den Rijn","C9V 0ZG","0845 46 41","iaculis.nec@sem.co.uk",57655);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Kaden","Levine","1965-06-19","P.O. Box 787, 7363 Penatibus St.","Huntsville","G97 7TI","0906 455 5808","Donec.nibh@mollis.net",63997);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Lois","Garner","1957-04-16","887-4470 Mauris St.","Roux-Miroir","N8 9UP","0845 46 47","tristique.aliquet@Integer.com",69398);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Hanna","Moses","1954-06-22","615-6451 Donec Road","Gosnells","FH78 9FN","0500 440099","pede@aliquameros.org",61582);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Blair","Newman","1978-06-06","661-2463 Non, Rd.","Palermo","OC3 8YT","076 1806 3920","ante.iaculis.nec@dictumcursusNunc.co.uk",51119);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Chanda","Baker","1975-07-18","576-4704 Consectetuer Street","Pontedera","R1P 2ED","0800 1111","In@quamdignissim.edu",20939);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Angelica","Guy","1980-10-13","Ap #926-1665 Malesuada Street","NÃ®mes","R2 2VJ","0938 537 7371","rhoncus.Proin@lobortisrisus.co.uk",31976);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Yoshi","Watson","1963-11-21","142-5328 Dapibus Street","Rostock","JG9 6SQ","056 0010 4751","libero@ultrices.com",24969);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Baxter","Baxter","1950-10-18","1081 Vitae Road","Chambave","MZ3 4KA","0800 1111","ut.aliquam@inaliquet.ca",25278);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Curran","Huffman","1979-06-18","2380 Urna. Rd.","Mobile","GI7F 6HJ","0800 1111","ipsum.dolor@non.co.uk",17150);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Sacha","Valenzuela","1953-10-26","P.O. Box 494, 8842 Velit Avenue","Hollange","LZ4 3AD","0385 737 7314","convallis.ante.lectus@nondui.org",50962);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Daquan","Flynn","1962-01-08","9823 Suscipit St.","Mesa","Y2 4OD","0977 443 0849","at.augue@ullamcorpermagna.ca",67840);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Eleanor","Payne","1961-10-07","7054 Ut, Rd.","Perwez","LV89 5YL","0800 339 4550","a.enim@liberonec.edu",50769);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Melvin","Chandler","1987-01-10","325-1237 A Ave","Munich","Y0 1FP","07624 277141","penatibus@diamnunc.co.uk",33815);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Charles","Juarez","1953-09-18","Ap #599-8663 Semper Ave","Orta San Giulio","KV7 1ED","(0113) 358 8288","ante.lectus@urnaet.org",56209);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Yasir","Mccoy","1965-08-02","466-3671 Luctus Ave","Pictou","VA0O 9JR","0800 1111","eu@fermentummetusAenean.org",38462);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Leandra","Vincent","1975-11-01","178-1277 Lacus. St.","Campinas","P1B 5WL","0845 46 49","sagittis@non.edu",50310);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Maxwell","Farley","1986-02-22","Ap #141-8796 A, Av.","L?vis","DF75 2MY","0957 386 2943","tellus.eu@ridiculus.org",25287);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Yuri","Skinner","1979-01-24","645-9903 Erat Av.","Gatineau","MI78 9UT","07624 680387","vel.nisl@justo.org",63188);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Odette","Dudley","1956-09-14","P.O. Box 270, 4168 Purus. St.","Lï¿½neburg","N7C 7EK","0500 852486","sit.amet.massa@necurnaet.com",58821);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Alika","Carlson","1991-03-31","858-3129 Vitae Avenue","Rea","T0M 2SZ","0800 071143","a@diamPellentesque.co.uk",17572);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Nora","Faulkner","1952-06-28","Ap #412-5178 Lectus Street","North Las Vegas","A2X 1JU","07624 781140","lacus@Nuncmaurissapien.com",47573);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Deanna","Puckett","1964-03-03","190-718 Eros Rd.","Baie-Comeau","DZ1M 8SR","056 2177 0907","egestas@vitaerisusDuis.net",69889);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Charissa","Orr","1957-10-21","4367 Euismod St.","Venezia","QH8P 3UP","076 5693 7043","scelerisque@imperdieterat.net",24015);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Doris","Mayer","1978-12-04","2969 Pharetra. Rd.","Herentals","RU38 6RJ","07110 888477","elit@faucibuslectusa.edu",42719);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Nina","Lang","1985-09-15","Ap #434-5243 Nibh. Ave","Green Bay","Z43 9PD","(01066) 012850","est.Nunc.ullamcorper@dolorsitamet.com",68601);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Octavius","Pittman","1975-09-21","P.O. Box 461, 7381 Laoreet Av.","Arzano","TX09 1UG","(0112) 875 8887","odio@Vestibulumante.net",40365);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Hunter","Cash","1988-08-15","Ap #812-2380 Elit Av.","Tongerlo","EZ8 3KJ","0845 46 44","arcu.Vestibulum@ante.ca",61024);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Danielle","Reid","1992-09-05","8050 Auctor Street","Stevenage","RC7N 6PJ","0800 1111","dignissim.magna.a@CuraePhasellus.com",58471);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Sigourney","Mays","1989-02-17","P.O. Box 170, 3405 Quis, Street","Haguenau","OJ9 6ZH","076 3746 1117","velit.in@aliquetdiam.edu",39284);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Dacey","Whitfield","1974-08-22","P.O. Box 968, 9210 Gravida. Road","Kaaskerke","G2 1XZ","(0119) 392 4458","hendrerit.Donec@variuseteuismod.net",54441);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Zorita","Watson","1986-09-21","303-2505 Adipiscing Road","Bowling Green","H5I 0RJ","070 1057 0681","neque.Morbi.quis@elit.edu",39259);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Xena","Waller","1990-12-23","6611 Arcu Av.","Villa Latina","U9J 0HA","(0115) 278 3371","nascetur.ridiculus.mus@ullamcorperDuiscursus.net",21114);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Jasmine","Dunlap","1982-05-13","P.O. Box 659, 5728 Erat Ave","Trois-Riviï¿½res","S9 6OL","0340 051 9517","Donec@Morbimetus.edu",44293);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Ursula","Vaughan","1969-07-12","P.O. Box 194, 1007 Phasellus St.","Hilversum","E1 5TX","0313 682 9619","condimentum.Donec.at@Nunc.org",22749);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Keegan","Palmer","1993-07-31","726-1492 Neque Rd.","Vagli Sotto","AX2 8DO","0800 007 4869","amet.ornare.lectus@nonlacinia.ca",26633);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Quinn","Mcknight","1985-01-28","Ap #597-3598 Cras Avenue","Oppido Mamertina","P0 0HV","(0116) 597 0875","lobortis.Class@Suspendisseeleifend.org",26989);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Joseph","Barron","1978-08-10","197-3295 Dictum. Ave","St. Albert","Z5 3BX","(028) 3719 9277","nunc.nulla.vulputate@Cumsociis.co.uk",67322);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Cassandra","Watson","1993-07-19","104-571 Cursus. Street","New Orleans","K3 2FL","0308 431 5149","Aliquam.fringilla@eget.ca",55293);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Tara","Doyle","1979-01-23","1169 Nec, Rd.","Bristol","O4W 3BL","(0115) 914 7169","sapien@magnatellusfaucibus.co.uk",60231);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Richard","Sykes","1954-09-21","P.O. Box 650, 5364 Lobortis, Avenue","Leffinge","D7B 1RB","0334 416 8217","Suspendisse.tristique.neque@magnis.org",41492);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Selma","Warren","1975-07-08","P.O. Box 859, 4774 A, Rd.","Rio Saliceto","T29 7FD","(016977) 9337","Nunc.pulvinar.arcu@Phasellusinfelis.net",39412);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Xanthus","James","1969-11-18","Ap #585-129 Erat Street","Lustin","RM1Z 7ND","(0110) 285 9144","eu@Fusce.net",47794);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Zia","Atkins","1976-05-02","Ap #139-6279 Pellentesque Av.","Rimbey","GW4 6AC","0500 348170","molestie.Sed.id@litora.co.uk",62783);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Catherine","Santiago","1984-10-24","1672 Purus Ave","Fort St. John","AH8R 9JO","(010256) 10550","congue.a@orciPhasellus.co.uk",22021);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Megan","Lyons","1973-10-30","Ap #664-5556 Imperdiet, Street","Opglabbeek","ZE0I 1AC","056 5211 4556","augue.malesuada@Nam.co.uk",57391);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Dustin","Perez","1980-07-16","Ap #884-4182 Vivamus St.","Pellizzano","NE7 6AU","(013553) 96843","condimentum@Nam.com",67255);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Wylie","Mann","1972-07-22","8991 Quis Street","Wedel","LP93 1VL","0872 547 2259","iaculis.nec@nibhDonecest.edu",48431);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Kameko","Stewart","1989-10-07","P.O. Box 791, 2492 Egestas Av.","Villers-Perwin","GS8 7OW","(012359) 61254","nisl.Maecenas@sagittis.com",18978);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Ian","Young","1982-07-22","P.O. Box 338, 8901 Varius Street","Salt Lake City","VR90 5FN","(01107) 62323","rutrum@velitegetlaoreet.co.uk",55828);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Camille","Norris","1981-08-24","Ap #327-1159 Lorem Avenue","Valfabbrica","M8 4EJ","(021) 7404 1467","Ut@temporaugueac.edu",27716);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Tatyana","Clayton","1968-12-28","P.O. Box 546, 7149 Amet Street","Walsall","BW13 6VK","0800 775 6456","montes@infelisNulla.edu",63508);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Aaron","Hodge","1954-12-10","6287 Duis Av.","Fraser Lake","KV64 8QV","(01461) 139439","Maecenas@blanditcongue.ca",19215);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Rachel","Thompson","1955-03-09","Ap #985-6122 Sed Rd.","Town of Yarmouth","F6 7OS","(012395) 15049","fringilla.purus.mauris@nasceturridiculusmus.co.uk",59490);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Dustin","Kline","1966-04-24","940-3136 Ipsum. Ave","Klagenfurt","T0 5BY","070 3839 3217","elit.pharetra@mauriselit.org",42030);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Hiram","Burch","1970-09-30","Ap #311-3291 Aliquam Road","Carbonear","AJ30 9ZO","0354 869 5705","purus@antedictum.co.uk",35049);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Nathaniel","Franco","1957-05-07","Ap #234-629 A, St.","Sovizzo","GO9Y 0CM","(01516) 607584","sem.vitae@aliquet.com",27589);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Mohammad","Paul","1951-07-01","P.O. Box 697, 728 Sapien, St.","Saint-Dizier","U6 8HG","0500 983512","et.pede.Nunc@utmi.com",65821);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Jael","Gordon","1966-06-19","913-5580 Dis St.","Stafford","M52 1HF","0845 46 40","in@felisullamcorper.edu",65098);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Cherokee","Nolan","1963-10-14","P.O. Box 552, 5766 Nunc Road","Kampenhout","GE26 2YN","(01239) 501116","orci.lobortis.augue@egestaslaciniaSed.com",21896);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Gage","Walton","1956-02-07","5865 A Rd.","Stockport","B51 8TK","0800 295442","dolor.tempus@luctusetultrices.ca",32485);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Claudia","Stuart","1981-08-12","P.O. Box 420, 1092 Dui Street","Gavorrano","Z2 2DG","0800 1111","quis@massa.edu",54705);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Murphy","Marquez","1951-03-26","635-838 Enim. Ave","Ternitz","Q9 8XD","070 0807 9444","nec.ligula@cursusdiamat.org",22370);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Desirae","Farley","1957-06-08","606-2574 Lacinia Av.","Colwood","R3 1LK","(014795) 50344","Quisque@metusVivamus.com",49879);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Madeson","Powers","1964-09-28","P.O. Box 320, 8048 Aliquam Street","Windsor","U67 9UU","(0114) 475 0052","Curabitur.ut@placerataugueSed.ca",67749);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Otto","Glenn","1956-09-22","P.O. Box 151, 1923 Et St.","Jasper","L39 1KE","(0116) 963 1102","auctor.velit.eget@accumsan.com",50948);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Adena","York","1974-04-22","Ap #196-6242 Vitae Avenue","Oliver","V2 2CX","0500 042838","cursus.a.enim@etipsum.com",54838);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Micah","Dyer","1950-12-11","124-7177 Ut Avenue","Scheggino","P4 5CZ","(024) 9279 2827","non.dapibus.rutrum@Aeneangravida.net",30798);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Dane","Gray","1987-06-28","P.O. Box 820, 7493 Consequat Ave","Colloredo di Monte Albano","DJ5 8DJ","(016977) 9627","et.netus@Fusce.org",69777);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Adria","Goodman","1994-12-09","417-8113 Lorem. Road","Saint-Herblain","NH0 5AC","(0117) 929 2105","metus.sit.amet@ultriciesligulaNullam.com",27369);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Chantale","Owens","1966-10-02","425 Sem Ave","Venezia","F57 8VS","(01515) 555549","sem@lacusQuisquepurus.com",48090);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Wing","Clarke","1990-01-05","P.O. Box 338, 266 Quisque Av.","San Diego","P8 3PD","0800 785226","faucibus@lobortisrisusIn.net",46135);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Griffin","Jackson","1950-05-21","7234 Ligula Rd.","Tilff","O5N 1PU","0500 970148","amet.consectetuer.adipiscing@loremloremluctus.net",25557);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Noah","Cotton","1993-08-09","P.O. Box 188, 3537 Dictum Street","Gespeg","ZK9H 3VK","070 9705 5072","Cum.sociis@erosturpisnon.net",35925);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Maggy","Ryan","1966-11-28","P.O. Box 323, 6717 Velit Ave","Nocera Umbra","CO44 6BZ","0800 543 1814","sociis@turpis.co.uk",29898);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Janna","Tyson","1954-01-16","P.O. Box 583, 2289 Ridiculus Rd.","Bellegem","E1 6OO","(01898) 030306","ullamcorper.nisl.arcu@variusultrices.edu",37125);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Murphy","Phillips","1978-04-29","783-3558 Ornare, Ave","Matagami","L71 3FA","(016977) 7981","tellus@etpedeNunc.org",55695);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Cameron","Hyde","1956-01-30","6435 Non, Avenue","Irricana","DD1 8AA","0394 435 2050","velit@Phasellusnulla.edu",47280);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Zia","Stein","1956-06-07","Ap #974-567 Ornare Ave","New Orleans","BS2P 1RN","0800 017 0190","ac@nislarcuiaculis.edu",41256);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Leigh","Campos","1961-04-13","Ap #262-7992 Nec Rd.","Cisterna di Latina","NH4A 3WC","0979 278 8133","erat.vitae.risus@Pellentesque.ca",25581);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Unity","Elliott","1994-08-07","404-4644 Nulla Av.","Zaltbommel","Q2O 8JM","056 4737 8656","erat.Sed@sagittissemperNam.co.uk",45718);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Yuli","Johns","1954-06-03","4478 Inceptos Av.","Lampernisse","BM2D 6TE","07472 599635","vel.turpis.Aliquam@atiaculisquis.com",26915);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Pamela","Lane","1991-07-16","703-8263 Pharetra St.","Rhayader","GB9P 1TK","07624 933513","quis.massa@lobortis.net",20770);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Faith","Washington","1967-11-16","P.O. Box 143, 1082 At, Av.","Adelaide","GR31 1JU","(027) 8268 8227","amet.dapibus.id@mattisornare.co.uk",30255);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Amos","Jordan","1992-06-28","3612 Phasellus St.","Poggiorsini","NS9I 3TM","076 2453 3662","nisi.magna.sed@fermentumconvallis.co.uk",19949);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Nehru","Franks","1953-01-30","P.O. Box 906, 5321 Nec, St.","Geelong","Y89 0SJ","0800 1111","tempus.lorem@Donecfringilla.edu",65417);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Duncan","Bray","1959-01-02","Ap #994-6815 Cursus Street","Oppido Mamertina","P9A 2PD","0994 496 8368","parturient.montes@dictumplacerataugue.net",17919);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Brett","Stanley","1964-06-15","5405 Commodo Rd.","Lakeshore","Z8 6VJ","0845 46 46","non.sollicitudin@milacinia.edu",58945);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Danielle","Allison","1979-03-17","9573 Eu Avenue","Nordhorn","RD8 3FV","(016977) 3568","posuere@ultricesVivamus.edu",52326);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Shana","Rocha","1963-01-01","2542 Convallis Rd.","Malonne","F3 8HY","(019865) 83226","ut@consequatauctor.com",17975);
INSERT INTO `staff` (`firstname`,`surname`,`dob`,`street_address`,`town`,`postcode`,`mobile`,`email`,`salary`) VALUES ("Isaiah","Olsen","1971-08-23","P.O. Box 428, 2300 Fermentum Rd.","BÃ©ziers","OX0B 4MB","(027) 6561 1418","non.quam.Pellentesque@et.org",43016);

INSERT INTO products (name, description, cost, rrp) VALUES ('Card', 'This card is the superior card in a set of cards. If you play this card against a player with another card, you win instantly', 5, 30);
INSERT INTO products (name, description, cost, rrp) VALUES ('Spikeshoes', 'These shoes are made for the manliest of men. Use with caution', 50, 125);
INSERT INTO products (name, description, cost, rrp) VALUES ('Coffee', 'Intended for use when developing in Java and SQL. Do not drink too much', 1, 20);
INSERT INTO products (name, description, cost, rrp) VALUES ('Phone', 'The newest Nokia makes you the coolest kid in class', 2000, 3000);
INSERT INTO products (name, description, cost, rrp) VALUES ('Chair', 'Sit comfortably as a Java developer on this magnificent chair', 20, 100);
INSERT INTO products (name, description, cost, rrp) VALUES ('Laptop', 'Laptop 13" from Apple ', 7000, 13000);
INSERT INTO products (name, description, cost, rrp) VALUES ('Desktop Computer', 'A monster gaming computer', 10000, 20000);
INSERT INTO products (name, description, cost, rrp) VALUES ('Iron Maiden First CD', 'A classic CD from the classic band', 50, 130);
INSERT INTO products (name, description, cost, rrp) VALUES ('Computer Bag', 'Perfect for use in rainy weather conditions', 200, 500);
INSERT INTO products (name, description, cost, rrp) VALUES ('Notebook', 'Write down your whole life with this notebook', 70, 150);
INSERT INTO products (name, description, cost, rrp) VALUES ('Car', 'Great car suited for a business man', 100000, 500000);

INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("Duis.cursus@elit.com","ante","Jillian","Brady","383-8726 Tempor Avenue","08628-727","Perwez","1-385-461-6140");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("dictum.placerat.augue@nullaatsem.org","Duis","Gabriel","Herrera","623-5456 Fermentum Rd.","1627HP","Lauregno/Laurein","1-558-565-9706");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("eget.volutpat@urnaNunc.com","nisl.","Fiona","Wilson","396-4191 Parturient Street","C3B 8W6","CÃ¡diz","1-718-813-7921");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("Phasellus@vulputateposuere.co.uk","erat","Jason","Dominguez","P.O. Box 808, 9853 Interdum Avenue","WQ2O 7SS","Merthyr Tydfil","1-853-740-5031");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("ultrices.a.auctor@amet.com","ornare","Martena","Boone","Ap #383-8640 Velit. Road","4270","Cumberland","1-969-956-8642");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("montes.nascetur@imperdietdictummagna.edu","lacus.","Regan","King","5823 In, Ave","2053ZV","Borgomasino","1-568-828-8306");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("libero@Vivamus.ca","Ut","Bertha","Wood","Ap #739-5342 Ut Av.","51598","Orosei","1-651-629-6551");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("sapien.gravida@dolorquamelementum.net","nulla","Stella","Newton","9962 Lectus. Ave","48410","Fort Smith","1-478-103-7148");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("dui.nec@faucibusorciluctus.co.uk","natoque","Judith","Mcgowan","494-2933 Consectetuer Road","18447","New Galloway","1-948-706-6813");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("lorem@antebibendumullamcorper.com","dolor.","Cherokee","Blackwell","P.O. Box 775, 7486 Per Rd.","20125","Deschambault","1-815-525-0097");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("et@vehiculaPellentesque.net","tincidunt","Flynn","Conrad","947-4335 Nunc Road","4905","Souvret","1-695-667-4044");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("velit@ut.org","dui.","Vance","Hines","104-5385 Et, Avenue","8080","Staï¿½furt","1-910-242-5638");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("euismod@ametornarelectus.co.uk","malesuada","Wallace","Abbott","Ap #487-6174 Placerat Rd.","2429YL","Aguacaliente (San Francisco)","1-204-712-6022");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("orci.luctus.et@eu.org","pharetra,","Ifeoma","Guerra","7432 Pede, Avenue","1882","Cerami","1-862-312-1678");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("elit.pellentesque@sociosquadlitora.net","scelerisque","Basil","Mccullough","4426 Lobortis St.","Q9 3WZ","Dampicourt","1-281-327-7110");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("sem@felisadipiscingfringilla.ca","egestas.","Lars","Hodge","5481 Nunc St.","41873-038","South Burlington","1-520-490-7190");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("tempor.augue@nuncullamcorper.net","non,","Sage","Price","Ap #918-4418 Lacus St.","5611EM","Columbus","1-823-575-6113");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("in.sodales@acfermentum.co.uk","eu,","Ezra","Shaffer","Ap #921-9210 Luctus Av.","76118","Limbach-Oberfrohna","1-155-395-3308");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("Sed@ullamcorperDuiscursus.org","Nulla","Halla","Boyd","Ap #638-5824 Urna, Av.","6597","Fontaine-l'Evï¿½que","1-898-259-9389");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("lorem.vehicula.et@rutrumjusto.edu","tempus,","Lionel","Duffy","P.O. Box 784, 1420 Lorem Av.","2396","Koblenz","1-642-392-8748");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("lorem.eget.mollis@orciPhasellus.edu","ac","Lamar","Cannon","272-5669 Vulputate, St.","RB76 5DK","Gresham","1-331-949-4076");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("netus@Nullafacilisis.net","sit","Rafael","Roberts","6846 Faucibus Rd.","5706","South Bend","1-639-635-7827");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("erat@Nullam.edu","vitae","Colleen","Reed","P.O. Box 235, 5957 In Avenue","4705","Lac La Biche County","1-240-569-9048");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("eget.mollis@Cras.co.uk","pede","Benjamin","Harrington","6093 Bibendum. St.","70653","Columbia","1-704-765-9749");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("aliquet.magna.a@pulvinar.edu","dictum","Eric","Beach","P.O. Box 618, 3768 Venenatis Street","33728","Forres","1-594-604-2973");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("elementum@parturientmontesnascetur.edu","Duis","Chaim","Fleming","7382 Mollis Rd.","L5C 3S0","Cartagena","1-878-842-0631");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("Nam@tincidunt.ca","quis","Murphy","Baxter","Ap #837-8653 Lectus. Road","Y6 3UQ","Sint-Kruis-Winkel","1-766-735-4939");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("eget.metus@hendrerit.net","fermentum","Ronan","Day","206-4089 Dignissim Av.","75471","Wokingham","1-862-521-3198");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("et@sodales.net","adipiscing","Rosalyn","Snider","P.O. Box 560, 1432 Tempor St.","NF4C 5ZF","East Gwillimbury","1-609-973-7236");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("tortor.Integer.aliquam@amalesuada.co.uk","arcu.","Ferdinand","Gilbert","Ap #415-9915 Sapien, Avenue","38166-542","Inuvik","1-334-167-3480");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("pretium@purusin.net","dignissim","Xena","Hobbs","Ap #845-521 Ac Street","54972","Laval","1-890-290-1683");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("Fusce@Integer.edu","scelerisque","Lucy","Joyner","431-7639 Lacinia Ave","92346","Stilo","1-858-925-3788");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("metus@eutellus.org","odio.","Keely","Dejesus","1740 Arcu. Road","T3J 1V9","Saint-Malo","1-289-581-8856");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("suscipit.est.ac@Phasellusdapibus.net","eget","Tara","Navarro","723-4270 Vivamus Rd.","87505","Portigliola","1-947-455-7169");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("In.at.pede@sitamet.org","amet,","Harriet","Reilly","5891 Maecenas Road","4700HZ","Wolfurt","1-717-294-5147");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("accumsan.laoreet@acmetus.edu","eleifend","Graiden","Pate","Ap #272-9110 Aliquam Road","54552","Stene","1-836-658-4289");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("euismod@orciquislectus.net","Pellentesque","Nash","Neal","Ap #427-1698 Tortor. St.","8205FS","Ceranesi","1-446-448-7834");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("dui.Fusce.diam@In.net","lobortis","Rhea","Mooney","P.O. Box 540, 4381 Augue Rd.","5450","Hertford","1-834-840-6603");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("Cras@ligula.ca","Cum","Aurelia","Montoya","5068 Natoque Road","61153","Borriana","1-425-437-1809");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("nisl@convallis.net","mollis","Ahmed","Taylor","P.O. Box 608, 6504 Erat Rd.","50187","March","1-255-696-9713");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("tempor@eget.net","arcu","Rajah","Prince","477-7227 Est, Av.","8788","Avise","1-377-608-6422");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("tellus@Donecconsectetuer.edu","accumsan","Tanner","Vasquez","P.O. Box 963, 9107 Lorem Road","16083-282","Moricone","1-547-380-3918");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("adipiscing.ligula.Aenean@vulputate.net","odio.","Palmer","Wall","Ap #416-9390 Est Ave","41950-441","Neerharen","1-993-370-2081");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("est.vitae@nunc.edu","Aenean","Baxter","Wise","352-7700 Aliquet St.","73349","Merksplas","1-390-148-2852");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("libero.Proin@justoProin.com","cubilia","Nehru","Wiggins","529-8238 Vivamus Rd.","06772","Hastings","1-943-148-0446");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("pretium.aliquet.metus@nibhAliquam.edu","aliquet.","Medge","Wong","Ap #920-1119 Augue. St.","Y6S 6KO","Acquasparta","1-653-538-8735");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("Fusce@nequeNullamnisl.net","turpis","Maite","Morin","P.O. Box 781, 4450 Nonummy Av.","76363","Jerez de la Frontera","1-771-273-0288");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("risus.quis@semNulla.co.uk","vulputate","Daphne","Rhodes","993-3177 Non, St.","8399","Moorsel","1-249-296-4564");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("vel.venenatis@Aliquamauctorvelit.edu","Quisque","Cheryl","Henry","Ap #268-6214 Gravida Road","9047","Osimo","1-902-757-1703");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("aliquet.libero.Integer@fermentum.ca","auctor","Martena","Newman","Ap #607-5656 Vitae, Rd.","T0P 3P8","Stewart","1-159-573-8979");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("ac.arcu.Nunc@Vestibulum.com","eget,","Yvonne","Maynard","Ap #938-6870 Porta Street","V05 7JZ","Ravenstein","1-739-801-3099");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("Curae@diamdictum.co.uk","auctor,","Gay","Dillard","331-3880 Tincidunt Street","4135EG","Wanneroo","1-343-373-7361");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("cursus.Nunc@convallisantelectus.com","quis","Denise","Dotson","313 Et Rd.","4278XR","Sint-Pieters-Kapelle","1-718-515-6761");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("Nullam.lobortis@ipsumnuncid.com","nascetur","Inga","Orr","6937 Vitae Road","8733","Klemskerke","1-590-753-3131");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("Proin.nisl.sem@egetmollis.com","tincidunt.","Dara","Alvarez","P.O. Box 214, 7106 Non St.","9104","Kitimat","1-477-781-4747");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("ligula.Nullam.feugiat@ipsumprimis.com","Curabitur","Eleanor","Ramos","989-9369 Etiam St.","1673","Blehen","1-129-721-2838");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("magnis.dis@sitamet.com","cursus","Patricia","Leblanc","1029 Mauris Road","76280","Leernes","1-298-658-2282");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("lacus@egetipsum.com","erat.","Lael","Carrillo","P.O. Box 218, 1328 Velit. Street","P8T 9N3","Legal","1-337-969-7540");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("tempus.non.lacinia@ipsum.org","Vivamus","Regan","Langley","9040 Aliquam St.","1713","Annan","1-356-388-2555");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("Suspendisse.sed@erosnectellus.edu","nisi","Charity","Herring","Ap #629-8701 Et St.","22908","Gloucester","1-246-700-6958");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("tristique.senectus.et@nequeNullamnisl.org","orci","Anjolie","Mercado","P.O. Box 414, 1193 Non Street","1775TI","Drongen","1-482-568-8240");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("magna.Ut@perinceptos.ca","nec","Maite","Jenkins","Ap #590-8382 Nec, St.","AX9N 7SV","Pace del Mela","1-768-623-5656");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("nascetur.ridiculus@telluseuaugue.org","Aliquam","Daquan","Evans","Ap #855-4499 Egestas. Avenue","95961-515","San Lorenzo","1-350-804-4240");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("ac.arcu@iaculisodio.co.uk","quam","Eugenia","Hawkins","P.O. Box 513, 1654 Cursus St.","4563MR","Grand Island","1-553-735-7618");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("ut.dolor@Aliquamnec.com","elit","Clayton","Nguyen","Ap #149-7357 Proin Street","1513","Port Hope","1-392-510-3011");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("facilisis@pellentesquemassalobortis.edu","et,","Yvonne","Mccormick","543-8364 At St.","6668YW","Castelluccio Valmaggiore","1-777-112-2274");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("nunc.sed@interdum.com","inceptos","Regan","Spence","P.O. Box 537, 4449 Arcu St.","67305","Abolens","1-588-624-0489");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("fringilla.est.Mauris@IncondimentumDonec.edu","nec,","Cadman","Mcknight","Ap #708-6169 Varius St.","92774","Cartagena","1-920-948-9080");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("at.lacus@ligulaAenean.com","nulla","Germaine","Carey","900-7203 Etiam St.","VZ17 5BJ","Madrid","1-490-410-8364");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("nulla.magna.malesuada@semsempererat.ca","tristique","Courtney","Hodge","752-6317 Adipiscing Rd.","9032SA","Gravilias","1-315-458-3031");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("tellus.sem@neque.com","cursus","Deacon","Bond","Ap #967-4609 Interdum. Rd.","19789","Ourense","1-170-481-4851");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("magna.malesuada@vitaeorci.com","hymenaeos.","Melanie","Alvarez","726-8047 Duis Ave","66928","Otricoli","1-663-242-9238");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("tincidunt@Duisat.net","sagittis.","Kuame","Reeves","Ap #848-9051 Diam Ave","2942","Noragugume","1-978-832-0148");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("pharetra.Nam@eleifendCrassed.ca","lorem.","Desirae","Velazquez","726-201 Donec Av.","97902","Guardia Sanframondi","1-542-495-7697");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("malesuada@etnetuset.co.uk","semper","Malik","Kirkland","365-7288 Ornare St.","8692","Casacalenda","1-429-707-8852");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("ornare.Fusce@lobortis.org","mi","Celeste","Daugherty","910-4865 Metus. St.","11414","AlenÃ§on","1-281-837-3442");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("malesuada.augue.ut@mi.org","dolor.","Nicholas","Lawson","783-5113 Libero Rd.","3016","Wabamun","1-715-105-6792");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("aliquet.sem.ut@convallisante.edu","nisl.","Joelle","Parrish","P.O. Box 674, 7511 Fusce Av.","YS93 8IP","Brugge Bruges","1-150-894-3837");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("eget@magna.co.uk","elementum,","Brock","Lawson","3484 Nascetur Avenue","8977FL","Ortonovo","1-335-781-9448");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("Nam@liberolacusvarius.edu","pede","Sean","Rojas","Ap #388-3086 Dui. Road","9486","Meridian","1-185-268-7333");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("accumsan.neque.et@euodio.net","netus","Octavia","Franklin","Ap #262-1408 Tincidunt Road","72274","Brixton","1-285-288-1154");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("mollis@pulvinar.co.uk","dictum.","Chester","Hoover","518-5356 Faucibus Rd.","30507","Hearst","1-969-750-7169");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("tincidunt.congue.turpis@ametnulla.edu","ipsum.","Belle","Summers","9175 Aliquam Ave","48185","Shawinigan","1-470-246-8626");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("sed.sapien.Nunc@enim.org","Proin","Signe","Barron","P.O. Box 737, 8589 Metus St.","4730","Scanzano Jonico","1-408-422-4219");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("semper.dui.lectus@erosnectellus.org","dolor","Wang","Bennett","P.O. Box 161, 3647 Diam. Av.","73859","Neerglabbeek","1-207-294-8733");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("Nunc@lacusCras.edu","ullamcorper,","Deacon","Cabrera","Ap #562-5428 Ultricies Ave","J2T 0E8","Gaithersburg","1-406-662-8775");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("Fusce.fermentum@nibhlaciniaorci.com","nec","Cleo","Gay","P.O. Box 643, 7554 Magna. Av.","7793MR","Lille","1-766-267-8221");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("fringilla@tellusNunc.org","blandit","Geoffrey","Combs","P.O. Box 151, 2278 Donec Road","6904","Midlands","1-934-217-9376");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("eleifend@tellusnon.com","dictum.","Madaline","Holder","3825 Amet Av.","2982","Johnstone","1-431-256-7928");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("Aliquam.auctor@hymenaeosMauris.edu","vestibulum.","Charde","Lawson","P.O. Box 998, 2494 Varius. St.","R6K 5G7","Itapipoca","1-738-677-8171");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("neque.vitae.semper@porttitortellus.edu","venenatis","Erich","Boyd","Ap #789-5026 Et St.","8933","La Magdeleine","1-105-543-0582");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("odio.Nam.interdum@amifringilla.co.uk","vel","Virginia","Phelps","Ap #470-9801 Lectus St.","3784","Narcao","1-838-710-7152");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("arcu.vel@aliquetlibero.co.uk","non","Casey","Lowery","827-4288 Sollicitudin Street","65571","Town of Yarmouth","1-477-368-6751");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("molestie.dapibus@maurissit.org","ligula","Avye","Stuart","P.O. Box 527, 5750 Id Av.","25732","Paulatuk","1-371-358-8513");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("urna@atlibero.ca","sollicitudin","Ishmael","Mccullough","7457 Urna St.","4878","West Valley City","1-925-470-5035");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("Nam@tempus.co.uk","in","Isaiah","Gilliam","P.O. Box 923, 458 Convallis Ave","Y3P 2XD","Grangemouth","1-406-804-8825");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("turpis.vitae.purus@nec.co.uk","dui","Steel","Alexander","P.O. Box 242, 5492 Gravida. St.","70258","Waitakere","1-857-847-7942");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("Ut.tincidunt@Crasdolor.co.uk","Etiam","Elvis","Garrison","Ap #800-5017 Phasellus Rd.","H5P 4E8","Kitimat","1-472-954-5562");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("Etiam@consectetuermaurisid.net","aliquet.","Chantale","Stephenson","P.O. Box 994, 5604 Risus. Avenue","31580","Builth Wells","1-406-130-9088");
INSERT INTO users (email,password,firstname,surname,street_address,postcode,town,phonenumber) VALUES ("ac.mattis.semper@nonummy.co.uk","nec","Dane","Dean","Ap #208-3615 Aliquam Rd.","6025OZ","Langford","1-186-814-0653");

INSERT INTO categories (name, staff_responsible) VALUES ('Books', 27);
INSERT INTO categories (name, staff_responsible) VALUES ('Computing', 58);
INSERT INTO categories (name, staff_responsible) VALUES ('Toys', 62);
INSERT INTO categories (name, staff_responsible) VALUES ('Gardening', 63);
INSERT INTO categories (name, staff_responsible) VALUES ('Electronics', 58);
INSERT INTO categories (name, staff_responsible) VALUES ('Clothes', 63);
INSERT INTO categories (name, staff_responsible) VALUES ('Mens', 63);
INSERT INTO categories (name, staff_responsible) VALUES ('Womens', 63);
INSERT INTO categories (name, staff_responsible) VALUES ('Children', 63);
INSERT INTO categories (name, staff_responsible) VALUES ('Sports & Outdoors', 71);
INSERT INTO categories (name, staff_responsible) VALUES ('Films', 27);

INSERT INTO products_in_categories (product_id, category_id)
VALUES (7, 2);

INSERT INTO products_in_categories (product_id, category_id)
VALUES (7, 3);

INSERT INTO products_in_categories (product_id, category_id)
VALUES (11, 3);

INSERT INTO products_in_categories (product_id, category_id)
VALUES (1, 3);

INSERT INTO products_in_categories (product_id, category_id)
VALUES (2, 10);

INSERT INTO products_in_categories (product_id, category_id)
VALUES (3, 10);

INSERT INTO products_in_categories (product_id, category_id)
VALUES (4, 5);

INSERT INTO products_in_categories (product_id, category_id)
VALUES (5, 4);

INSERT INTO products_in_categories (product_id, category_id)
VALUES (5, 10);

INSERT INTO products_in_categories (product_id, category_id)
VALUES (6, 2);

INSERT INTO products_in_categories (product_id, category_id)
VALUES (6, 5);

INSERT INTO products_in_categories (product_id, category_id)
VALUES (8, 11);

INSERT INTO products_in_categories (product_id, category_id)
VALUES (8, 3);

INSERT INTO products_in_categories (product_id, category_id)
VALUES (9, 2);

INSERT INTO products_in_categories (product_id, category_id)
VALUES (10, 1);


INSERT INTO carts (user_id, product_id, quantity)
VALUES (5, 3, 10);

INSERT INTO carts (user_id, product_id, quantity)
VALUES (20, 7, 2);

INSERT INTO carts (user_id, product_id, quantity)
VALUES (20, 8, 10);