INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('d9f4b834-1a89-4c2b-b1a7-6b1c7a8c3e9a' AS UUID), 'user1', 'ci_user1', true, 'BLOOD_PRESSURE',
     '{
        "logType": "BLOOD_PRESSURE",
        "createTime": "2025-03-05T08:00:00Z",
        "updateTime": "2025-03-05T08:00:00Z",
        "startTime": "2025-03-05T08:00:00Z",
        "isActive": true,
        "systolic": "xv+R/7AblaW8cVvUg6CE+w==",
        "diastolic": "0zNwLzWOG3txV6K7axT1GA=="
     }'::jsonb, 'NORMAL_BP', '2025-03-05 08:00:00', '2025-03-05 08:00:00', '2025-03-05 08:00:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('a1f75c62-7f2b-4c0c-8d3a-1a2b9e0f5c3d' AS UUID), 'user1', 'ci_user1', true, 'BLOOD_PRESSURE',
     '{
        "logType": "BLOOD_PRESSURE",
        "createTime": "2025-03-05T20:00:00Z",
        "updateTime": "2025-03-05T20:00:00Z",
        "startTime": "2025-03-05T20:00:00Z",
        "isActive": true,
        "systolic": "QZ7gR+9pR5Bkm8Epc7Czww==",
        "diastolic": "B7jW8BRK5KkGn+39nTxKMg=="
     }'::jsonb, 'HIGH_BP', '2025-03-05 20:00:00', '2025-03-05 20:00:00', '2025-03-05 20:00:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('b3e98d12-4b5e-4a7b-93a1-7d3f4a2b8e6c' AS UUID), 'user1', 'ci_user1', true, 'BLOOD_PRESSURE',
     '{
        "logType": "BLOOD_PRESSURE",
        "createTime": "2025-03-10T09:00:00Z",
        "updateTime": "2025-03-10T09:00:00Z",
        "startTime": "2025-03-10T09:00:00Z",
        "isActive": true,
        "systolic": "T5Lh8h/sB3KdQc6kE2AJeQ==",
        "diastolic": "l4dZT9cE1eLx4HjNZj/d1A=="
     }'::jsonb, 'LOW_BP', '2025-03-10 09:00:00', '2025-03-10 09:00:00', '2025-03-10 09:00:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('c7d2f1a3-5e6b-49f7-a1d3-9b7e4c5d2a1f' AS UUID), 'user1', 'ci_user1', true, 'BLOOD_PRESSURE',
     '{
        "logType": "BLOOD_PRESSURE",
        "createTime": "2025-03-10T18:30:00Z",
        "updateTime": "2025-03-10T18:30:00Z",
        "startTime": "2025-03-10T18:30:00Z",
        "isActive": true,
        "systolic": "xRj1ZUIc6ZP9KaB1FUPwXg==",
        "diastolic": "cJ2UyjGc/V1c0rVTXOlfpA=="
     }'::jsonb, 'HIGH_BP', '2025-03-10 18:30:00', '2025-03-10 18:30:00', '2025-03-10 18:30:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('f2e3c4d5-6a7b-4c8d-9e0f-1a2b3c4d5e6f' AS UUID), 'user1', 'ci_user1', true, 'BLOOD_PRESSURE',
     '{
        "logType": "BLOOD_PRESSURE",
        "createTime": "2025-03-15T07:45:00Z",
        "updateTime": "2025-03-15T07:45:00Z",
        "startTime": "2025-03-15T07:45:00Z",
        "isActive": true,
        "systolic": "K5J4GgP2r5v/1QbB/M7d7A==",
        "diastolic": "H+6Z7rYIx7cZx/RgXx/P/w=="
     }'::jsonb, 'NORMAL_BP', '2025-03-15 07:45:00', '2025-03-15 07:45:00', '2025-03-15 07:45:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('0a1b2c3d-4e5f-6789-abcd-ef0123456789' AS UUID), 'user1', 'ci_user1', true, 'BLOOD_PRESSURE',
     '{
        "logType": "BLOOD_PRESSURE",
        "createTime": "2025-03-15T21:00:00Z",
        "updateTime": "2025-03-15T21:00:00Z",
        "startTime": "2025-03-15T21:00:00Z",
        "isActive": true,
        "systolic": "A3sJ5LqPzY7lR0xX7jGQ5A==",
        "diastolic": "s2uI8Q6HdU7wR1jF5JQ0Vg=="
     }'::jsonb, 'HIGH_BP', '2025-03-15 21:00:00', '2025-03-15 21:00:00', '2025-03-15 21:00:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('1b2c3d4e-5f6a-789b-cdef-0123456789ab' AS UUID), 'user1', 'ci_user1', true, 'BLOOD_PRESSURE',
     '{
        "logType": "BLOOD_PRESSURE",
        "createTime": "2025-03-20T08:30:00Z",
        "updateTime": "2025-03-20T08:30:00Z",
        "startTime": "2025-03-20T08:30:00Z",
        "isActive": true,
        "systolic": "VJxX/zSBA7Dq/sY0Zjk1AA==",
        "diastolic": "4FqH2z9aS8Bv/P2c0fYZtg=="
     }'::jsonb, 'NORMAL_BP', '2025-03-20 08:30:00', '2025-03-20 08:30:00', '2025-03-20 08:30:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('2c3d4e5f-6a7b-89ab-cdef-123456789abc' AS UUID), 'user1', 'ci_user1', true, 'BLOOD_PRESSURE',
     '{
        "logType": "BLOOD_PRESSURE",
        "createTime": "2025-03-20T19:15:00Z",
        "updateTime": "2025-03-20T19:15:00Z",
        "startTime": "2025-03-20T19:15:00Z",
        "isActive": true,
        "systolic": "mN+vR9dF7GtYbP+Z1/4oNw==",
        "diastolic": "kR1yB6sL8C3Zq7hT9xLp2g=="
     }'::jsonb, 'HIGH_BP', '2025-03-20 19:15:00', '2025-03-20 19:15:00', '2025-03-20 19:15:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('3d4e5f6a-7b8c-9abd-ef01-23456789abcd' AS UUID), 'user1', 'ci_user1', true, 'BLOOD_PRESSURE',
     '{
        "logType": "BLOOD_PRESSURE",
        "createTime": "2025-03-25T07:30:00Z",
        "updateTime": "2025-03-25T07:30:00Z",
        "startTime": "2025-03-25T07:30:00Z",
        "isActive": true,
        "systolic": "PqO7vRqI1j3Z+PlQ8ksjAA==",
        "diastolic": "XqL9s5CrI8Kl7B3Q6kR0bA=="
     }'::jsonb, 'NORMAL_BP', '2025-03-25 07:30:00', '2025-03-25 07:30:00', '2025-03-25 07:30:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('4e5f6a7b-8c9d-abcd-ef01-3456789abcde' AS UUID), 'user1', 'ci_user1', true, 'BLOOD_PRESSURE',
     '{
        "logType": "BLOOD_PRESSURE",
        "createTime": "2025-03-25T21:00:00Z",
        "updateTime": "2025-03-25T21:00:00Z",
        "startTime": "2025-03-25T21:00:00Z",
        "isActive": true,
        "systolic": "F3eJ8nRqI4m5K6nP7oQ1Aa==",
        "diastolic": "T6uI4mQrI9p2V8gH5dK0g=="
     }'::jsonb, 'HIGH_BP', '2025-03-25 21:00:00', '2025-03-25 21:00:00', '2025-03-25 21:00:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('5f6a7b8c-9d0e-abcd-ef12-456789abcdef' AS UUID), 'user1', 'ci_user1', true, 'BLOOD_PRESSURE',
     '{
        "logType": "BLOOD_PRESSURE",
        "createTime": "2025-03-30T08:15:00Z",
        "updateTime": "2025-03-30T08:15:00Z",
        "startTime": "2025-03-30T08:15:00Z",
        "isActive": true,
        "systolic": "G7eH9mSqI5p4O6nA7bK1Bb==",
        "diastolic": "W5vJ7lQrI4m3T2pC8sL0fA=="
     }'::jsonb, 'NORMAL_BP', '2025-03-30 08:15:00', '2025-03-30 08:15:00', '2025-03-30 08:15:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('6a7b8c9d-0e1f-abcd-ef23-56789abcdef0' AS UUID), 'user1', 'ci_user1', true, 'BLOOD_PRESSURE',
     '{
        "logType": "BLOOD_PRESSURE",
        "createTime": "2025-03-30T20:00:00Z",
        "updateTime": "2025-03-30T20:00:00Z",
        "startTime": "2025-03-30T20:00:00Z",
        "isActive": true,
        "systolic": "Z9kJ4vQrI7m8X2sB3tR1Cc==",
        "diastolic": "Y8mL5wSrI6p9V1gF2uS0g=="
     }'::jsonb, 'HIGH_BP', '2025-03-30 20:00:00', '2025-03-30 20:00:00', '2025-03-30 20:00:00');


INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('7a8b9c10-11d2-4e13-15f6-17a8b9c10d11' AS UUID), 'user1', 'ci_user1', true, 'STEP_COUNT',
     '{
        "logType": "STEP_COUNT",
        "createTime": "2025-03-03T07:00:00Z",
        "updateTime": "2025-03-03T07:00:00Z",
        "startTime": "2025-03-03T07:00:00Z",
        "isActive": true,
        "stepCount": 10000,
        "calorie": 300.5
     }'::jsonb, 'NORMAL', '2025-03-03 07:00:00', '2025-03-03 07:00:00', '2025-03-03 07:00:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('8b9c10d1-12e3-4f14-15f6-17a8b9c10d12' AS UUID), 'user1', 'ci_user1', true, 'STEP_COUNT',
     '{
        "logType": "STEP_COUNT",
        "createTime": "2025-03-08T07:15:00Z",
        "updateTime": "2025-03-08T07:15:00Z",
        "startTime": "2025-03-08T07:15:00Z",
        "isActive": true,
        "stepCount": 12000,
        "calorie": 350.0
     }'::jsonb, 'NORMAL', '2025-03-08 07:15:00', '2025-03-08 07:15:00', '2025-03-08 07:15:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('9c10d1e2-23f4-4a15-15f6-17a8b9c10d13' AS UUID), 'user1', 'ci_user1', true, 'STEP_COUNT',
     '{
        "logType": "STEP_COUNT",
        "createTime": "2025-03-13T07:30:00Z",
        "updateTime": "2025-03-13T07:30:00Z",
        "startTime": "2025-03-13T07:30:00Z",
        "isActive": true,
        "stepCount": 8000,
        "calorie": 250.0
     }'::jsonb, 'NORMAL', '2025-03-13 07:30:00', '2025-03-13 07:30:00', '2025-03-13 07:30:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('ad10e2f3-34b5-4c16-15f6-17a8b9c10d14' AS UUID), 'user1', 'ci_user1', true, 'STEP_COUNT',
     '{
        "logType": "STEP_COUNT",
        "createTime": "2025-03-18T07:45:00Z",
        "updateTime": "2025-03-18T07:45:00Z",
        "startTime": "2025-03-18T07:45:00Z",
        "isActive": true,
        "stepCount": 15000,
        "calorie": 400.0
     }'::jsonb, 'NORMAL', '2025-03-18 07:45:00', '2025-03-18 07:45:00', '2025-03-18 07:45:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('be10f304-45c6-4d17-15f6-17a8b9c10d15' AS UUID), 'user1', 'ci_user1', true, 'STEP_COUNT',
     '{
        "logType": "STEP_COUNT",
        "createTime": "2025-03-23T08:00:00Z",
        "updateTime": "2025-03-23T08:00:00Z",
        "startTime": "2025-03-23T08:00:00Z",
        "isActive": true,
        "stepCount": 9000,
        "calorie": 280.0
     }'::jsonb, 'NORMAL', '2025-03-23 08:00:00', '2025-03-23 08:00:00', '2025-03-23 08:00:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('cf10a415-56d7-4e18-15f6-17a8b9c10d16' AS UUID), 'user1', 'ci_user1', true, 'STEP_COUNT',
     '{
        "logType": "STEP_COUNT",
        "createTime": "2025-03-28T08:15:00Z",
        "updateTime": "2025-03-28T08:15:00Z",
        "startTime": "2025-03-28T08:15:00Z",
        "isActive": true,
        "stepCount": 11000,
        "calorie": 320.0
     }'::jsonb, 'NORMAL', '2025-03-28 08:15:00', '2025-03-28 08:15:00', '2025-03-28 08:15:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('da10b526-67e8-4f19-15f6-17a8b9c10d17' AS UUID), 'user1', 'ci_user1', true, 'STEP_COUNT',
     '{
        "logType": "STEP_COUNT",
        "createTime": "2025-03-30T08:30:00Z",
        "updateTime": "2025-03-30T08:30:00Z",
        "startTime": "2025-03-30T08:30:00Z",
        "isActive": true,
        "stepCount": 9500,
        "calorie": 290.0
     }'::jsonb, 'NORMAL', '2025-03-30 08:30:00', '2025-03-30 08:30:00', '2025-03-30 08:30:00');


INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('e1f2a3b4-c5d6-4e7f-89a0-b1c2d3e4f5a6' AS UUID), 'user1', 'ci_user1', true, 'WEIGHT',
     '{
        "logType": "WEIGHT",
        "createTime": "2025-03-04T09:00:00Z",
        "updateTime": "2025-03-04T09:00:00Z",
        "startTime": "2025-03-04T09:00:00Z",
        "isActive": true,
        "weight": 68.5,
        "height": 172.3
     }'::jsonb, 'NORMAL', '2025-03-04 09:00:00', '2025-03-04 09:00:00', '2025-03-04 09:00:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('f1e2d3c4-b5a6-4f7e-89c0-d1e2f3a4b5c6' AS UUID), 'user1', 'ci_user1', true, 'WEIGHT',
     '{
        "logType": "WEIGHT",
        "createTime": "2025-03-10T09:30:00Z",
        "updateTime": "2025-03-10T09:30:00Z",
        "startTime": "2025-03-10T09:30:00Z",
        "isActive": true,
        "weight": 69.0,
        "height": 172.3
     }'::jsonb, 'NORMAL', '2025-03-10 09:30:00', '2025-03-10 09:30:00', '2025-03-10 09:30:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('a2b3c4d5-e6f7-4a8b-89c0-d1e2f3a4b5c7' AS UUID), 'user1', 'ci_user1', true, 'WEIGHT',
     '{
        "logType": "WEIGHT",
        "createTime": "2025-03-16T09:15:00Z",
        "updateTime": "2025-03-16T09:15:00Z",
        "startTime": "2025-03-16T09:15:00Z",
        "isActive": true,
        "weight": 68.0,
        "height": 172.3
     }'::jsonb, 'NORMAL', '2025-03-16 09:15:00', '2025-03-16 09:15:00', '2025-03-16 09:15:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('b3c4d5e6-f7a8-4b9c-89d0-e1f2a3b4c5d8' AS UUID), 'user1', 'ci_user1', true, 'WEIGHT',
     '{
        "logType": "WEIGHT",
        "createTime": "2025-03-22T10:00:00Z",
        "updateTime": "2025-03-22T10:00:00Z",
        "startTime": "2025-03-22T10:00:00Z",
        "isActive": true,
        "weight": 68.8,
        "height": 172.3
     }'::jsonb, 'NORMAL', '2025-03-22 10:00:00', '2025-03-22 10:00:00', '2025-03-22 10:00:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('c4d5e6f7-a8b9-4c0d-89e0-f1a2b3c4d5e9' AS UUID), 'user1', 'ci_user1', true, 'WEIGHT',
     '{
        "logType": "WEIGHT",
        "createTime": "2025-03-28T10:30:00Z",
        "updateTime": "2025-03-28T10:30:00Z",
        "startTime": "2025-03-28T10:30:00Z",
        "isActive": true,
        "weight": 68.2,
        "height": 172.3
     }'::jsonb, 'NORMAL', '2025-03-28 10:30:00', '2025-03-28 10:30:00', '2025-03-28 10:30:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('d5e6f7a8-b9c0-4d1e-89f0-a1b2c3d4e5f0' AS UUID), 'user1', 'ci_user1', true, 'WEIGHT',
     '{
        "logType": "WEIGHT",
        "createTime": "2025-04-01T09:45:00Z",
        "updateTime": "2025-04-01T09:45:00Z",
        "startTime": "2025-04-01T09:45:00Z",
        "isActive": true,
        "weight": 68.6,
        "height": 172.3
     }'::jsonb, 'NORMAL', '2025-04-01 09:45:00', '2025-04-01 09:45:00', '2025-04-01 09:45:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('e6f7a8b9-c0d1-4e2f-89a1-b2c3d4e5f6a1' AS UUID), 'user1', 'ci_user1', true, 'WEIGHT',
     '{
        "logType": "WEIGHT",
        "createTime": "2025-04-05T10:15:00Z",
        "updateTime": "2025-04-05T10:15:00Z",
        "startTime": "2025-04-05T10:15:00Z",
        "isActive": true,
        "weight": 68.4,
        "height": 172.3
     }'::jsonb, 'NORMAL', '2025-04-05 10:15:00', '2025-04-05 10:15:00', '2025-04-05 10:15:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('f7a8b9c0-d1e2-4f3a-89b2-c3d4e5f6a7b2' AS UUID), 'user1', 'ci_user1', true, 'WEIGHT',
     '{
        "logType": "WEIGHT",
        "createTime": "2025-04-10T10:30:00Z",
        "updateTime": "2025-04-10T10:30:00Z",
        "startTime": "2025-04-10T10:30:00Z",
        "isActive": true,
        "weight": 68.7,
        "height": 172.3
     }'::jsonb, 'NORMAL', '2025-04-10 10:30:00', '2025-04-10 10:30:00', '2025-04-10 10:30:00');


INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('a1234567-89ab-4cde-f012-3456789abcde' AS UUID), 'user2', 'ci_user2', true, 'BLOOD_PRESSURE',
     '{
        "logType": "BLOOD_PRESSURE",
        "createTime": "2025-03-06T08:15:00Z",
        "updateTime": "2025-03-06T08:15:00Z",
        "startTime": "2025-03-06T08:15:00Z",
        "isActive": true,
        "systolic": "p8zLk4yD5R7sL1F1g/VQ6g==",
        "diastolic": "i9w4Cbl9ix+sdm6t2z9fLw=="
     }'::jsonb, 'NORMAL_BP', '2025-03-06 08:15:00', '2025-03-06 08:15:00', '2025-03-06 08:15:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('b2345678-9abc-5def-0123-456789abcdef' AS UUID), 'user2', 'ci_user2', true, 'BLOOD_PRESSURE',
     '{
        "logType": "BLOOD_PRESSURE",
        "createTime": "2025-03-06T20:00:00Z",
        "updateTime": "2025-03-06T20:00:00Z",
        "startTime": "2025-03-06T20:00:00Z",
        "isActive": true,
        "systolic": "A3sJ5LqPzY7lR0xX7jGQ5A==",
        "diastolic": "s2uI8Q6HdU7wR1jF5JQ0Vg=="
     }'::jsonb, 'HIGH_BP', '2025-03-06 20:00:00', '2025-03-06 20:00:00', '2025-03-06 20:00:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('c3456789-abcd-6ef0-1234-56789abcdef0' AS UUID), 'user2', 'ci_user2', true, 'BLOOD_PRESSURE',
     '{
        "logType": "BLOOD_PRESSURE",
        "createTime": "2025-03-11T09:00:00Z",
        "updateTime": "2025-03-11T09:00:00Z",
        "startTime": "2025-03-11T09:00:00Z",
        "isActive": true,
        "systolic": "T5Lh8h/sB3KdQc6kE2AJeQ==",
        "diastolic": "l4dZT9cE1eLx4HjNZj/d1A=="
     }'::jsonb, 'LOW_BP', '2025-03-11 09:00:00', '2025-03-11 09:00:00', '2025-03-11 09:00:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('d456789a-bcde-7f01-2345-6789abcdef01' AS UUID), 'user2', 'ci_user2', true, 'BLOOD_PRESSURE',
     '{
        "logType": "BLOOD_PRESSURE",
        "createTime": "2025-03-16T18:30:00Z",
        "updateTime": "2025-03-16T18:30:00Z",
        "startTime": "2025-03-16T18:30:00Z",
        "isActive": true,
        "systolic": "xRj1ZUIc6ZP9KaB1FUPwXg==",
        "diastolic": "cJ2UyjGc/V1c0rVTXOlfpA=="
     }'::jsonb, 'HIGH_BP', '2025-03-16 18:30:00', '2025-03-16 18:30:00', '2025-03-16 18:30:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('e56789ab-cdef-8f12-3456-789abcdef012' AS UUID), 'user2', 'ci_user2', true, 'BLOOD_PRESSURE',
     '{
        "logType": "BLOOD_PRESSURE",
        "createTime": "2025-03-21T07:45:00Z",
        "updateTime": "2025-03-21T07:45:00Z",
        "startTime": "2025-03-21T07:45:00Z",
        "isActive": true,
        "systolic": "K5J4GgP2r5v/1QbB/M7d7A==",
        "diastolic": "H+6Z7rYIx7cZx/RgXx/P/w=="
     }'::jsonb, 'NORMAL_BP', '2025-03-21 07:45:00', '2025-03-21 07:45:00', '2025-03-21 07:45:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('f6789abc-def0-9f23-4567-89abcdef0123' AS UUID), 'user2', 'ci_user2', true, 'BLOOD_PRESSURE',
     '{
        "logType": "BLOOD_PRESSURE",
        "createTime": "2025-03-26T21:00:00Z",
        "updateTime": "2025-03-26T21:00:00Z",
        "startTime": "2025-03-26T21:00:00Z",
        "isActive": true,
        "systolic": "A3sJ5LqPzY7lR0xX7jGQ5A==",
        "diastolic": "s2uI8Q6HdU7wR1jF5JQ0Vg=="
     }'::jsonb, 'HIGH_BP', '2025-03-26 21:00:00', '2025-03-26 21:00:00', '2025-03-26 21:00:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('01234567-89ab-cdef-0123-456789abcdef' AS UUID), 'user2', 'ci_user2', true, 'BLOOD_PRESSURE',
     '{
        "logType": "BLOOD_PRESSURE",
        "createTime": "2025-03-28T08:30:00Z",
        "updateTime": "2025-03-28T08:30:00Z",
        "startTime": "2025-03-28T08:30:00Z",
        "isActive": true,
        "systolic": "VJxX/zSBA7Dq/sY0Zjk1AA==",
        "diastolic": "4FqH2z9aS8Bv/P2c0fYZtg=="
     }'::jsonb, 'NORMAL_BP', '2025-03-28 08:30:00', '2025-03-28 08:30:00', '2025-03-28 08:30:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('12345678-9abc-def0-1234-56789abcdef0' AS UUID), 'user2', 'ci_user2', true, 'BLOOD_PRESSURE',
     '{
        "logType": "BLOOD_PRESSURE",
        "createTime": "2025-03-30T19:15:00Z",
        "updateTime": "2025-03-30T19:15:00Z",
        "startTime": "2025-03-30T19:15:00Z",
        "isActive": true,
        "systolic": "mN+vR9dF7GtYbP+Z1/4oNw==",
        "diastolic": "kR1yB6sL8C3Zq7hT9xLp2g=="
     }'::jsonb, 'HIGH_BP', '2025-03-30 19:15:00', '2025-03-30 19:15:00', '2025-03-30 19:15:00');


INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('23456789-abcd-ef01-2345-6789abcdef01' AS UUID), 'user2', 'ci_user2', true, 'STEP_COUNT',
     '{
        "logType": "STEP_COUNT",
        "createTime": "2025-03-04T07:00:00Z",
        "updateTime": "2025-03-04T07:00:00Z",
        "startTime": "2025-03-04T07:00:00Z",
        "isActive": true,
        "stepCount": 11000,
        "calorie": 320.0
     }'::jsonb, 'NORMAL', '2025-03-04 07:00:00', '2025-03-04 07:00:00', '2025-03-04 07:00:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('3456789a-bcde-f012-3456-789abcdef012' AS UUID), 'user2', 'ci_user2', true, 'STEP_COUNT',
     '{
        "logType": "STEP_COUNT",
        "createTime": "2025-03-09T07:15:00Z",
        "updateTime": "2025-03-09T07:15:00Z",
        "startTime": "2025-03-09T07:15:00Z",
        "isActive": true,
        "stepCount": 13000,
        "calorie": 370.0
     }'::jsonb, 'NORMAL', '2025-03-09 07:15:00', '2025-03-09 07:15:00', '2025-03-09 07:15:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('456789ab-cdef-0123-4567-89abcdef0123' AS UUID), 'user2', 'ci_user2', true, 'STEP_COUNT',
     '{
        "logType": "STEP_COUNT",
        "createTime": "2025-03-14T07:30:00Z",
        "updateTime": "2025-03-14T07:30:00Z",
        "startTime": "2025-03-14T07:30:00Z",
        "isActive": true,
        "stepCount": 9000,
        "calorie": 290.0
     }'::jsonb, 'NORMAL', '2025-03-14 07:30:00', '2025-03-14 07:30:00', '2025-03-14 07:30:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('56789abc-def0-1234-5678-9abcdef01234' AS UUID), 'user2', 'ci_user2', true, 'STEP_COUNT',
     '{
        "logType": "STEP_COUNT",
        "createTime": "2025-03-19T07:45:00Z",
        "updateTime": "2025-03-19T07:45:00Z",
        "startTime": "2025-03-19T07:45:00Z",
        "isActive": true,
        "stepCount": 14000,
        "calorie": 410.0
     }'::jsonb, 'NORMAL', '2025-03-19 07:45:00', '2025-03-19 07:45:00', '2025-03-19 07:45:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('6789abcd-ef01-2345-6789-abcdef012345' AS UUID), 'user2', 'ci_user2', true, 'STEP_COUNT',
     '{
        "logType": "STEP_COUNT",
        "createTime": "2025-03-24T08:00:00Z",
        "updateTime": "2025-03-24T08:00:00Z",
        "startTime": "2025-03-24T08:00:00Z",
        "isActive": true,
        "stepCount": 8000,
        "calorie": 260.0
     }'::jsonb, 'NORMAL', '2025-03-24 08:00:00', '2025-03-24 08:00:00', '2025-03-24 08:00:00');


INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('789abcde-f012-3456-789a-bcdef0123456' AS UUID), 'user2', 'ci_user2', true, 'WEIGHT',
     '{
        "logType": "WEIGHT",
        "createTime": "2025-03-05T09:00:00Z",
        "updateTime": "2025-03-05T09:00:00Z",
        "startTime": "2025-03-05T09:00:00Z",
        "isActive": true,
        "weight": 75.0,
        "height": 175.0
     }'::jsonb, 'NORMAL', '2025-03-05 09:00:00', '2025-03-05 09:00:00', '2025-03-05 09:00:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('89abcdef-0123-4567-89ab-cdef01234567' AS UUID), 'user2', 'ci_user2', true, 'WEIGHT',
     '{
        "logType": "WEIGHT",
        "createTime": "2025-03-15T09:30:00Z",
        "updateTime": "2025-03-15T09:30:00Z",
        "startTime": "2025-03-15T09:30:00Z",
        "isActive": true,
        "weight": 75.5,
        "height": 175.0
     }'::jsonb, 'NORMAL', '2025-03-15 09:30:00', '2025-03-15 09:30:00', '2025-03-15 09:30:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('9abcdef0-1234-5678-9abc-def012345678' AS UUID), 'user2', 'ci_user2', true, 'WEIGHT',
     '{
        "logType": "WEIGHT",
        "createTime": "2025-03-25T10:00:00Z",
        "updateTime": "2025-03-25T10:00:00Z",
        "startTime": "2025-03-25T10:00:00Z",
        "isActive": true,
        "weight": 74.8,
        "height": 175.0
     }'::jsonb, 'NORMAL', '2025-03-25 10:00:00', '2025-03-25 10:00:00', '2025-03-25 10:00:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('abcdef01-2345-6789-abcd-ef0123456789' AS UUID), 'user2', 'ci_user2', true, 'WEIGHT',
     '{
        "logType": "WEIGHT",
        "createTime": "2025-03-30T10:30:00Z",
        "updateTime": "2025-03-30T10:30:00Z",
        "startTime": "2025-03-30T10:30:00Z",
        "isActive": true,
        "weight": 75.2,
        "height": 175.0
     }'::jsonb, 'NORMAL', '2025-03-30 10:30:00', '2025-03-30 10:30:00', '2025-03-30 10:30:00');


INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('bcdef012-3456-789a-bcde-f0123456789a' AS UUID), 'user3', 'ci_user3', true, 'BLOOD_PRESSURE',
     '{
        "logType": "BLOOD_PRESSURE",
        "createTime": "2025-03-02T08:00:00Z",
        "updateTime": "2025-03-02T08:00:00Z",
        "startTime": "2025-03-02T08:00:00Z",
        "isActive": true,
        "systolic": "xv+R/7AblaW8cVvUg6CE+w==",
        "diastolic": "0zNwLzWOG3txV6K7axT1GA=="
     }'::jsonb, 'NORMAL_BP', '2025-03-02 08:00:00', '2025-03-02 08:00:00', '2025-03-02 08:00:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('cdef0123-4567-89ab-cdef-0123456789ab' AS UUID), 'user3', 'ci_user3', true, 'BLOOD_PRESSURE',
     '{
        "logType": "BLOOD_PRESSURE",
        "createTime": "2025-03-07T20:00:00Z",
        "updateTime": "2025-03-07T20:00:00Z",
        "startTime": "2025-03-07T20:00:00Z",
        "isActive": true,
        "systolic": "QZ7gR+9pR5Bkm8Epc7Czww==",
        "diastolic": "B7jW8BRK5KkGn+39nTxKMg=="
     }'::jsonb, 'HIGH_BP', '2025-03-07 20:00:00', '2025-03-07 20:00:00', '2025-03-07 20:00:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('def01234-5678-9abc-def0-123456789abc' AS UUID), 'user3', 'ci_user3', true, 'BLOOD_PRESSURE',
     '{
        "logType": "BLOOD_PRESSURE",
        "createTime": "2025-03-12T09:00:00Z",
        "updateTime": "2025-03-12T09:00:00Z",
        "startTime": "2025-03-12T09:00:00Z",
        "isActive": true,
        "systolic": "T5Lh8h/sB3KdQc6kE2AJeQ==",
        "diastolic": "l4dZT9cE1eLx4HjNZj/d1A=="
     }'::jsonb, 'LOW_BP', '2025-03-12 09:00:00', '2025-03-12 09:00:00', '2025-03-12 09:00:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('ef012345-6789-abcd-ef01-23456789abcd' AS UUID), 'user3', 'ci_user3', true, 'BLOOD_PRESSURE',
     '{
        "logType": "BLOOD_PRESSURE",
        "createTime": "2025-03-17T18:30:00Z",
        "updateTime": "2025-03-17T18:30:00Z",
        "startTime": "2025-03-17T18:30:00Z",
        "isActive": true,
        "systolic": "xRj1ZUIc6ZP9KaB1FUPwXg==",
        "diastolic": "cJ2UyjGc/V1c0rVTXOlfpA=="
     }'::jsonb, 'HIGH_BP', '2025-03-17 18:30:00', '2025-03-17 18:30:00', '2025-03-17 18:30:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('f0123456-789a-bcde-f012-3456789abcde' AS UUID), 'user3', 'ci_user3', true, 'BLOOD_PRESSURE',
     '{
        "logType": "BLOOD_PRESSURE",
        "createTime": "2025-03-22T07:45:00Z",
        "updateTime": "2025-03-22T07:45:00Z",
        "startTime": "2025-03-22T07:45:00Z",
        "isActive": true,
        "systolic": "K5J4GgP2r5v/1QbB/M7d7A==",
        "diastolic": "H+6Z7rYIx7cZx/RgXx/P/w=="
     }'::jsonb, 'NORMAL_BP', '2025-03-22 07:45:00', '2025-03-22 07:45:00', '2025-03-22 07:45:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('01234567-89ab-cdef-0123-456789abcdea' AS UUID), 'user3', 'ci_user3', true, 'BLOOD_PRESSURE',
     '{
        "logType": "BLOOD_PRESSURE",
        "createTime": "2025-03-27T21:00:00Z",
        "updateTime": "2025-03-27T21:00:00Z",
        "startTime": "2025-03-27T21:00:00Z",
        "isActive": true,
        "systolic": "A3sJ5LqPzY7lR0xX7jGQ5A==",
        "diastolic": "s2uI8Q6HdU7wR1jF5JQ0Vg=="
     }'::jsonb, 'HIGH_BP', '2025-03-27 21:00:00', '2025-03-27 21:00:00', '2025-03-27 21:00:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('12345678-9abc-def0-1234-56789abcdeab' AS UUID), 'user3', 'ci_user3', true, 'BLOOD_PRESSURE',
     '{
        "logType": "BLOOD_PRESSURE",
        "createTime": "2025-03-29T08:30:00Z",
        "updateTime": "2025-03-29T08:30:00Z",
        "startTime": "2025-03-29T08:30:00Z",
        "isActive": true,
        "systolic": "VJxX/zSBA7Dq/sY0Zjk1AA==",
        "diastolic": "4FqH2z9aS8Bv/P2c0fYZtg=="
     }'::jsonb, 'NORMAL_BP', '2025-03-29 08:30:00', '2025-03-29 08:30:00', '2025-03-29 08:30:00');


INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('23456789-abcd-2345-6789-cdef01234567' AS UUID), 'user3', 'ci_user3', true, 'BLOOD_PRESSURE',
     '{
        "logType": "BLOOD_PRESSURE",
        "createTime": "2025-03-31T19:15:00Z",
        "updateTime": "2025-03-31T19:15:00Z",
        "startTime": "2025-03-31T19:15:00Z",
        "isActive": true,
        "systolic": "mN+vR9dF7GtYbP+Z1/4oNw==",
        "diastolic": "kR1yB6sL8C3Zq7hT9xLp2g=="
     }'::jsonb, 'HIGH_BP', '2025-03-31 19:15:00', '2025-03-31 19:15:00', '2025-03-31 19:15:00');


-- [STEP_COUNT] User3 (5건)
-- 문제의 UUID들 재구성: 모든 경우 raw 32자리(hex)로 재분할 (8-4-4-4-12)
INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('34567890-abcd-3456-789a-bcdef0123456' AS UUID), 'user3', 'ci_user3', true, 'STEP_COUNT',
     '{
        "logType": "STEP_COUNT",
        "createTime": "2025-03-03T07:00:00Z",
        "updateTime": "2025-03-03T07:00:00Z",
        "startTime": "2025-03-03T07:00:00Z",
        "isActive": true,
        "stepCount": 9500,
        "calorie": 310.0
     }'::jsonb, 'NORMAL', '2025-03-03 07:00:00', '2025-03-03 07:00:00', '2025-03-03 07:00:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('45678901-bcde-4567-89ab-cdef01234567' AS UUID), 'user3', 'ci_user3', true, 'STEP_COUNT',
     '{
        "logType": "STEP_COUNT",
        "createTime": "2025-03-08T07:15:00Z",
        "updateTime": "2025-03-08T07:15:00Z",
        "startTime": "2025-03-08T07:15:00Z",
        "isActive": true,
        "stepCount": 10500,
        "calorie": 330.0
     }'::jsonb, 'NORMAL', '2025-03-08 07:15:00', '2025-03-08 07:15:00', '2025-03-08 07:15:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('56789012-cdef-5678-9abc-def012345678' AS UUID), 'user3', 'ci_user3', true, 'STEP_COUNT',
     '{
        "logType": "STEP_COUNT",
        "createTime": "2025-03-13T07:30:00Z",
        "updateTime": "2025-03-13T07:30:00Z",
        "startTime": "2025-03-13T07:30:00Z",
        "isActive": true,
        "stepCount": 11500,
        "calorie": 360.0
     }'::jsonb, 'NORMAL', '2025-03-13 07:30:00', '2025-03-13 07:30:00', '2025-03-13 07:30:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('67890123-def1-6789-abcd-ef0123456789' AS UUID), 'user3', 'ci_user3', true, 'STEP_COUNT',
     '{
        "logType": "STEP_COUNT",
        "createTime": "2025-03-18T07:45:00Z",
        "updateTime": "2025-03-18T07:45:00Z",
        "startTime": "2025-03-18T07:45:00Z",
        "isActive": true,
        "stepCount": 12500,
        "calorie": 380.0
     }'::jsonb, 'NORMAL', '2025-03-18 07:45:00', '2025-03-18 07:45:00', '2025-03-18 07:45:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('78901234-ef12-789a-bcde-f0123456789a' AS UUID), 'user3', 'ci_user3', true, 'STEP_COUNT',
     '{
        "logType": "STEP_COUNT",
        "createTime": "2025-03-23T08:00:00Z",
        "updateTime": "2025-03-23T08:00:00Z",
        "startTime": "2025-03-23T08:00:00Z",
        "isActive": true,
        "stepCount": 10000,
        "calorie": 340.0
     }'::jsonb, 'NORMAL', '2025-03-23 08:00:00', '2025-03-23 08:00:00', '2025-03-23 08:00:00');


-- [WEIGHT] User3 (4건)
INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('89012345-f123-4567-89ab-cdef01234567' AS UUID), 'user3', 'ci_user3', true, 'WEIGHT',
     '{
        "logType": "WEIGHT",
        "createTime": "2025-03-04T09:00:00Z",
        "updateTime": "2025-03-04T09:00:00Z",
        "startTime": "2025-03-04T09:00:00Z",
        "isActive": true,
        "weight": 60.0,
        "height": 165.0
     }'::jsonb, 'NORMAL', '2025-03-04 09:00:00', '2025-03-04 09:00:00', '2025-03-04 09:00:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('90123456-0123-4567-89ab-cdef01234567' AS UUID), 'user3', 'ci_user3', true, 'WEIGHT',
     '{
        "logType": "WEIGHT",
        "createTime": "2025-03-14T09:30:00Z",
        "updateTime": "2025-03-14T09:30:00Z",
        "startTime": "2025-03-14T09:30:00Z",
        "isActive": true,
        "weight": 60.5,
        "height": 165.0
     }'::jsonb, 'NORMAL', '2025-03-14 09:30:00', '2025-03-14 09:30:00', '2025-03-14 09:30:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('01234567-1234-5678-9abc-23456789abcd' AS UUID), 'user3', 'ci_user3', true, 'WEIGHT',
     '{
        "logType": "WEIGHT",
        "createTime": "2025-03-24T10:00:00Z",
        "updateTime": "2025-03-24T10:00:00Z",
        "startTime": "2025-03-24T10:00:00Z",
        "isActive": true,
        "weight": 59.8,
        "height": 165.0
     }'::jsonb, 'NORMAL', '2025-03-24 10:00:00', '2025-03-24 10:00:00', '2025-03-24 10:00:00');

INSERT INTO lifelog (id, user_id, ci, is_active, log_type, payload, status, start_time, created_at, updated_at) VALUES
    (CAST('12345678-2345-6789-abcd-ef0123456789' AS UUID), 'user3', 'ci_user3', true, 'WEIGHT',
     '{
        "logType": "WEIGHT",
        "createTime": "2025-03-29T10:30:00Z",
        "updateTime": "2025-03-29T10:30:00Z",
        "startTime": "2025-03-29T10:30:00Z",
        "isActive": true,
        "weight": 60.2,
        "height": 165.0
     }'::jsonb, 'NORMAL', '2025-03-29 10:30:00', '2025-03-29 10:30:00', '2025-03-29 10:30:00');
