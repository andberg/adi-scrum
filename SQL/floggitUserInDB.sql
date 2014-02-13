CREATE USER 'floggit_admin'@'localhost' IDENTIFIED BY 'Floggit2014';

GRANT SELECT ON floggit.users TO 'floggit_admin'@'localhost';
GRANT SELECT ON floggit.products TO 'floggit_admin'@'localhost';
GRANT SELECT ON floggit.categories TO 'floggit_admin'@'localhost';
GRANT SELECT ON floggit.products_in_categories TO 'floggit_admin'@'localhost';
GRANT SELECT ON floggit.carts TO 'floggit_admin'@'localhost';

GRANT DELETE ON floggit.users TO 'floggit_admin'@'localhost';
GRANT DELETE ON floggit.products TO 'floggit_admin'@'localhost';
GRANT DELETE ON floggit.categories TO 'floggit_admin'@'localhost';
GRANT DELETE ON floggit.products_in_categories TO 'floggit_admin'@'localhost';
GRANT DELETE ON floggit.carts TO 'floggit_admin'@'localhost';

GRANT INSERT ON floggit.users TO 'floggit_admin'@'localhost';
GRANT INSERT ON floggit.products TO 'floggit_admin'@'localhost';
GRANT INSERT ON floggit.categories TO 'floggit_admin'@'localhost';
GRANT INSERT ON floggit.products_in_categories TO 'floggit_admin'@'localhost';
GRANT INSERT ON floggit.carts TO 'floggit_admin'@'localhost';

GRANT UPDATE ON floggit.users TO 'floggit_admin'@'localhost';
GRANT UPDATE ON floggit.products TO 'floggit_admin'@'localhost';
GRANT UPDATE ON floggit.categories TO 'floggit_admin'@'localhost';
GRANT UPDATE ON floggit.products_in_categories TO 'floggit_admin'@'localhost';
GRANT UPDATE ON floggit.carts TO 'floggit_admin'@'localhost';