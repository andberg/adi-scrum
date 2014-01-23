SELECT products.name, categories.name
FROM products INNER JOIN products_in_categories ON products.id = products_in_categories.product_id
INNER JOIN categories ON products_in_categories.category_id = categories.id; 