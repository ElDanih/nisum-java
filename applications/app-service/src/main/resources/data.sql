/*-- Usuario de prueba
INSERT INTO USER_ENTITY (
    id,
    name,
    email,
    password,
    created,
    modified,
    last_login,
    is_active
) VALUES (
             'b4d6aa12-5a0e-4f65-8617-123456789abc',
             'Juan Rodriguez',
             'juan@rodriguez.org',
             'Hunter2',
             CURRENT_TIMESTAMP,
             CURRENT_TIMESTAMP,
             CURRENT_TIMESTAMP,
             TRUE
         );

-- Teléfonos del usuario
INSERT INTO PHONE_ENTITY (
    id,
    number,
    citycode,
    contrycode,
    user_id
) VALUES
      (
          1,
          '1234567',
          '1',
          '57',
          'b4d6aa12-5a0e-4f65-8617-123456789abc'
      ),
      (
          2,
          '7654321',
          '2',
          '57',
          'b4d6aa12-5a0e-4f65-8617-123456789abc'
      );*/