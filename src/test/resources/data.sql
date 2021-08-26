INSERT INTO `employee_role` (
    `role_name`,
    `specification`,
    `capability_id`,
    `band_id`,
    `responsibility_id`)
VALUES
   ('Test Job Role', 'Test role spec 1', 0, 1, 2),
    ('Job Role 1', 'Role spec 1', 1, 0, 0),
    ('Job Role 2', 'Role spec 2', 2 ,2, 3);


INSERT INTO `capability` (
    `capability_name`
)
VALUES
('Engineering'),
('Artificial Intelligence');


INSERT INTO `job_family` (
    `job_family_name`,
    `capability_id`
)
VALUES
       ('Engineering Strategy and Planning', 1),
       ('Engineering', 1),
       ('Architecture', 2);


INSERT INTO `band` (band_id, band_name) values (
    1, 'test_band_name'
);
INSERT INTO `responsibility` (responsibility_id, responsibility_name) values (
    1, 'test_responsibility_name'
);
