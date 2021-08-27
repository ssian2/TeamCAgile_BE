

INSERT INTO `band` (band_id, band_name) values (
    1, 'test_band_name'
),(2,'next band name');


INSERT INTO `employee_role` (
    `role_name`,
    `specification`,
    `capability_id`,
    `band_id`,
    `responsibility_id`)
VALUES
    ('Job Role 1', 'Role spec 1', 1, 1, 0),
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


INSERT INTO `responsibility` (responsibility_id, responsibility_name) values (
    1, 'test_responsibility_name'
), (2,'another_test_resp');

INSERT INTO `responsibility_employee_role` (responsibility_id, role_id) values (1,1),(2,2);