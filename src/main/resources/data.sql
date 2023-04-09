INSERT INTO DEVICE (SYSTEM_NAME, DEVICE_TYPE)
VALUES ('Device 1', 'WINDOWS_WORKSTATION'),
       ('Device 2', 'WINDOWS_SERVER'),
       ('Device 3', 'MAC'),
       ('Device 4', 'MAC'),
       ('Device 5', 'MAC');

INSERT INTO RMM_SERVICE (COST, NAME, DEVICE_TYPE)
VALUES (4.00, 'Device', 'WINDOWS_WORKSTATION'),
       (4.00, 'Device', 'WINDOWS_SERVER'),
       (4.00, 'Device', 'MAC'),
       (4.00, 'Device', 'DEBIAN_SERVER'),
       (5.00, 'Antivirus', 'WINDOWS_WORKSTATION'),
       (5.00, 'Antivirus', 'WINDOWS_SERVER'),
       (7.00, 'Antivirus', 'MAC'),
       (3.00, 'Backup', 'WINDOWS_WORKSTATION'),
       (3.00, 'Backup', 'WINDOWS_SERVER'),
       (3.00, 'Backup', 'MAC'),
       (3.00, 'Backup', 'DEBIAN_SERVER'),
       (1.00, 'Screen Share', 'WINDOWS_WORKSTATION'),
       (1.00, 'Screen Share', 'WINDOWS_SERVER'),
       (1.00, 'Screen Share', 'MAC'),
       (1.00, 'Screen Share', 'DEBIAN_SERVER');

INSERT INTO RMM_SERVICE_EXECUTION (DEVICE_ID, RMM_SERVICE_ID, QUANTITY, EXECUTION_DATE_TIME)
--           devices contracted
VALUES (1, 1, 1, '2023-04-01T15:30:00'),
       (2, 2, 1, '2023-04-01T15:30:00'),
       (3, 3, 1, '2023-04-01T15:31:00'),
       (4, 3, 1, '2023-04-01T15:31:00'),
       (5, 3, 1, '2022-04-01T15:31:00'),
--                antivirus
       (1, 5, 2, '2022-04-01T15:32:00'),
       (3, 7, 1, '2022-04-01T15:32:00'),
       (4, 7, 1, '2022-04-01T15:32:00'),
       (5, 7, 1, '2022-04-01T15:32:00'),
--                 backup
       (2, 9, 1, '2022-04-01T15:33:00'),
       (3, 10, 1, '2022-04-01T15:33:00'),
       (5, 10, 1, '2022-04-01T15:33:00'),
--               screen share
       (1, 12, 2, '2022-04-01T15:35:00'),
       (3, 14, 2, '2022-04-01T15:35:00');




