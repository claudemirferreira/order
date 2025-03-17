-- Cria o banco de dados pedido-db (já criado automaticamente pelo MYSQL_DATABASE, mas pode ser recriado para garantir)
CREATE DATABASE IF NOT EXISTS `pedido-db`;

-- Cria o banco de dados pagamento-db
CREATE DATABASE IF NOT EXISTS `pagamento-db`;

-- Concede todos os privilégios ao usuário 'admin' nos bancos de dados
GRANT ALL PRIVILEGES ON `pedido-db`.* TO 'admin'@'%';
GRANT ALL PRIVILEGES ON `pagamento-db`.* TO 'admin'@'%';

-- Atualiza as permissões
FLUSH PRIVILEGES;