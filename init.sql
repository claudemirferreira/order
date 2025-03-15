-- Cria o banco de dados order-db
CREATE DATABASE IF NOT EXISTS `pedido-db`;

-- Cria o banco de dados payment-db
CREATE DATABASE IF NOT EXISTS `pagamento-db`;

-- Opcional: Concede permissões ao usuário myuser para os bancos de dados
GRANT ALL PRIVILEGES ON `pedido-db`.* TO 'admin'@'%';
GRANT ALL PRIVILEGES ON `pagamento-db`.* TO 'admin'@'%';
FLUSH PRIVILEGES;