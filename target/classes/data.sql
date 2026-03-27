-- ─────────────────────────────────────────────────────────────────────────────
-- Sales Management System — Sample Data
-- Run this against MySQL after creating the database:
--   CREATE DATABASE sales_db;
--   USE sales_db;
-- ─────────────────────────────────────────────────────────────────────────────

-- Passwords below are BCrypt hashes of "password123"

INSERT IGNORE INTO users (username, password, email, role, full_name, active) VALUES
('admin',      '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6TuW2', 'admin@sales.com',   'ADMIN',     'System Admin',   true),
('john.doe',   '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6TuW2', 'john@sales.com',    'SALES_REP', 'John Doe',       true),
('jane.smith', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6TuW2', 'jane@sales.com',    'SALES_REP', 'Jane Smith',     true),
('mike.mgr',   '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6TuW2', 'mike@sales.com',    'MANAGER',   'Mike Johnson',   true),
('sara.rep',   '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6TuW2', 'sara@sales.com',    'SALES_REP', 'Sara Williams',  true);

INSERT IGNORE INTO products (name, description, price, stock_quantity, category, sku, active) VALUES
('Laptop Pro 15',        'High-performance 15-inch laptop with Intel i7',     1299.99,  50,  'Electronics',  'SKU-LAPTOP-001', true),
('Wireless Mouse',       'Ergonomic wireless mouse with long battery life',      29.99, 200,  'Accessories',  'SKU-MOUSE-001',  true),
('Mechanical Keyboard',  'RGB mechanical keyboard with Cherry MX switches',      89.99, 150,  'Accessories',  'SKU-KBD-001',    true),
('4K Monitor 27"',       '27-inch 4K UHD IPS monitor with USB-C',              449.99,  30,  'Electronics',  'SKU-MON-001',    true),
('USB-C Hub 7-in-1',     'Multiport USB-C hub with HDMI and card reader',        49.99, 100,  'Accessories',  'SKU-HUB-001',    true),
('Noise-Cancel Headset', 'Over-ear ANC headset with 30h battery',              199.99,  75,  'Electronics',  'SKU-HEAD-001',   true),
('Webcam 1080p',         'Full HD webcam with built-in microphone',              79.99,  90,  'Electronics',  'SKU-CAM-001',    true),
('Office Chair Pro',     'Ergonomic mesh office chair with lumbar support',     349.99,  20,  'Furniture',    'SKU-CHAIR-001',  true),
('Standing Desk 60"',    'Motorized sit-stand desk with memory presets',        699.99,  10,  'Furniture',    'SKU-DESK-001',   true),
('Laptop Stand',         'Adjustable aluminium laptop stand, foldable',          39.99, 120,  'Accessories',  'SKU-STAND-001',  true);

INSERT IGNORE INTO sales (product_id, user_id, quantity, unit_price, total_amount, status, sale_date, customer_name, customer_email, notes) VALUES
(1, 2, 2, 1299.99, 2599.98, 'COMPLETED', '2026-01-15 10:30:00', 'Alice Brown',    'alice@example.com',   'Bulk order for office'),
(3, 2, 5,   89.99,  449.95, 'COMPLETED', '2026-01-18 14:00:00', 'Bob Carter',     'bob@example.com',     NULL),
(4, 3, 1,  449.99,  449.99, 'COMPLETED', '2026-01-22 09:15:00', 'Carol Davis',    'carol@example.com',   'Requested fast delivery'),
(2, 3, 10,  29.99,  299.90, 'COMPLETED', '2026-02-03 11:45:00', 'David Evans',    'david@example.com',   NULL),
(6, 2, 3,  199.99,  599.97, 'COMPLETED', '2026-02-10 16:00:00', 'Eva Foster',     'eva@example.com',     'Corporate account'),
(5, 5, 4,   49.99,  199.96, 'COMPLETED', '2026-02-14 13:30:00', 'Frank Green',    'frank@example.com',   NULL),
(8, 3, 1,  349.99,  349.99, 'COMPLETED', '2026-02-20 10:00:00', 'Grace Harris',   'grace@example.com',   'Assembly requested'),
(9, 2, 1,  699.99,  699.99, 'PENDING',   '2026-03-01 09:00:00', 'Henry Irving',   'henry@example.com',   'Awaiting payment'),
(7, 5, 2,   79.99,  159.98, 'COMPLETED', '2026-03-05 15:00:00', 'Isla Jones',     'isla@example.com',    NULL),
(10, 3, 6,  39.99,  239.94, 'COMPLETED', '2026-03-10 12:00:00', 'Jack King',      'jack@example.com',    'Volume discount applied'),
(1, 5, 1, 1299.99, 1299.99, 'COMPLETED', '2026-03-15 11:00:00', 'Karen Lee',      'karen@example.com',   NULL),
(2, 2, 3,   29.99,   89.97, 'CANCELLED', '2026-03-18 14:30:00', 'Liam Moore',     'liam@example.com',    'Customer cancelled'),
(4, 3, 2,  449.99,  899.98, 'COMPLETED', '2026-03-20 10:30:00', 'Mia Nelson',     'mia@example.com',     NULL),
(6, 5, 1,  199.99,  199.99, 'COMPLETED', '2026-03-22 09:45:00', 'Noah Owen',      'noah@example.com',    NULL),
(3, 2, 2,   89.99,  179.98, 'COMPLETED', '2026-03-25 16:00:00', 'Olivia Parker',  'olivia@example.com',  'Gift wrap requested');
