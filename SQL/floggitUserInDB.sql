GRANT SELECT ON floggit.* TO 'floggit_admin'@'localhost' IDENTIFIED BY 'Floggit2014';
GRANT DELETE, INSERT, UPDATE ON floggit.users TO 'floggit_admin'@'localhost';
GRANT DELETE, INSERT, UPDATE ON floggit.products TO 'floggit_admin'@'localhost';
GRANT DELETE, INSERT, UPDATE ON floggit.categories TO 'floggit_admin'@'localhost';
GRANT DELETE, INSERT, UPDATE ON floggit.products_in_categories TO 'floggit_admin'@'localhost';
GRANT DELETE, INSERT, UPDATE ON floggit.carts TO 'floggit_admin'@'localhost';