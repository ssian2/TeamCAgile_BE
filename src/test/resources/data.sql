
INSERT INTO `band` (band_id, band_name) values (
    1, 'test_band_name'
);

INSERT INTO `responsibility` (responsibility_id, responsibility_name) values (
    1, 'test_responsibility_name'
);

INSERT INTO `capability` (capability_id, capability_name) values (
    1, 'test_cap_name'
);



INSERT INTO `employee_role` (
    `role_name`,
    `specification`,
    `capability_id`,
    `band_id`,
    `responsibility_id`)
VALUES
    ('Job Role 1', 'Role spec 1', 1, 1, 1),
    ('Job Role 2', 'Role spec 2', 1 ,1, 1);