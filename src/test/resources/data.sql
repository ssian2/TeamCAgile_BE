INSERT INTO `employee_role` (
    `role_name`,
    `specification`,
    `capability_id`,
    `band_id`,
    `responsibility_id`)
VALUES
    ('Job Role 1', 'Role spec 1', 1, 0, 0),
    ('Job Role 2', 'Role spec 2', 2 ,2, 3);


INSERT INTO `capability` (
    `capability_name`
)
VALUES
('Engineering'),
('Artificial Intelligence');
