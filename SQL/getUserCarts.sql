SELECT users.firstname, users.surname, products.name, quantity
FROM users INNER JOIN carts ON users.id = carts.user_id
INNER JOIN products ON carts.product_id = products.id; 