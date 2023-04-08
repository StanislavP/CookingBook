-- some test users

INSERT INTO roles (id, user_role)
values
    (1, 'ADMIN'),
    (2, 'MODERATOR');

INSERT INTO users ( email, first_name, last_name, is_active, password)
VALUES
    ( 'admin@example.com', 'Admin', 'Adminov', 1, '$2a$10$YelHxdnNur5Wc3MKWuYA6OQGGTARcX1ub9z7F0Ty/5Tlm/80loZhi');


INSERT INTO users_roles (user_entity_id, roles_id)
VALUES
    (2, 1),
    (2, 2);


INSERT INTO measure_units (id, unit_type,unit_name)
VALUES (1, 'DESSERTSPOON','dsp.'),
       (2, 'TEASPOON','tsp.'),
       (3, 'TABLESPOON','tbsp.'),
       (4, 'COFFEESPOON','csp.'),
       (5, 'LITER','l'),
       (6, 'MILLILITER','ml'),
       (7, 'MILLIGRAM','mg'),
       (8, 'GRAM','g'),
       (9, 'KILOGRAM','kg'),
       (10, 'CUP','c');

INSERT INTO difficulties (id, difficulty,description)
VALUES (1, 'EASY', 'Requires little to basic cooking skills and common ingredients.'),
    (2, 'MODERATE', 'Requires more experience, more prep and cooking time, and maybe some ingredients you don’t already have in your kitchen.'),
    (3, 'HARD', 'Challenging recipes that require more advanced skills and experience and maybe some special equipment.');

INSERT INTO categories (id, category_type)
VALUES (1, 'FRUITS'),
       (2, 'VEGETABLES'),
       (3, 'GRAINS'),
       (4, 'MEATS'),
       (5, 'EGGS'),
       (6, 'SEAFOOD'),
       (7, 'NUTS'),
       (8, 'SEEDS'),
       (9, 'MILK'),
       (10, 'YOGURT'),
       (11, 'CHEESE');


