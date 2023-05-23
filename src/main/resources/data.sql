INSERT INTO televisions (id, type, brand, name, price, available_size, refresh_rate, screen_type, screen_quality, smart_tv, wifi, voice_control, hdr, bluetooth, ambi_light, original_stock, sold)
    VALUES (101, 'LED', 'Samsung', 'Samsung 32-inch LED TV', 399.99, 32.0, 60.0, 'Flat', 'Full HD', true, true, true, true, true, false, 100, 0);

INSERT INTO televisions (id, type, brand, name, price, available_size, refresh_rate, screen_type, screen_quality, smart_tv, wifi, voice_control, hdr, bluetooth, ambi_light, original_stock, sold)
VALUES (20, 'HML123', 'Huawei', 'Huawei Mini Led 123', 230.90, 30 , 60.0, 'Flat', 'Good', false, true, false, false, true, false, 50, 25);

INSERT INTO televisions (id, type, brand, name, price, available_size, refresh_rate, screen_type, screen_quality, smart_tv, wifi, voice_control, hdr, bluetooth, ambi_light, original_stock, sold)
VALUES (50, 'iOSA23', 'Apple', 'Apple iOS TV', 5000, 50.0 ,80.0, 'Wide', 'HDR', true, true, true, true, true, true, 20, 4);


INSERT INTO remote_controllers (id, compatible_with, battery_type, name, brand, price, stock)
VALUES (101, 'TV', 'AA', 'Samsung Mini Remote', 'Samsung', 20, 100);

INSERT INTO remote_controllers (id, compatible_with, battery_type, name, brand, price, stock)
VALUES (20, 'TV', 'AAA', 'Huawei remote SL', 'Huawei', 15, 100);


UPDATE televisions SET remotecontroller_id = '101' WHERE id = 101;
UPDATE televisions SET remotecontroller_id = '20' WHERE id = 20;

INSERT INTO ci_modules(id, name, price, type)
VALUES (3, 'Samsung CI', 30.00, 'SCI2');

INSERT INTO ci_modules(id, name, price, type)
VALUES (50, 'iOS Apple CI', 100, 'iOS Cx');

INSERT INTO wall_bracket(id, size, adjustable, name, price)
VALUES(101, '50 cm', true, 'General Wallbracket', 30)

-- // class enkelvoud
-- // tabel meervoud