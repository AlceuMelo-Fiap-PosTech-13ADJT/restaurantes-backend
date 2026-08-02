-- SQL inicial para ambiente dev

CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    nome_de_usuario VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    logradouro VARCHAR(255) NOT NULL,
    numero VARCHAR(255) NOT NULL,
    complemento VARCHAR(255),
    bairro VARCHAR(255),
    cidade VARCHAR(255) NOT NULL,
    estado VARCHAR(255) NOT NULL,
    cep VARCHAR(255) NOT NULL,
    perfil VARCHAR(255) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO usuarios (
    nome,
    email,
    nome_de_usuario,
    senha,
    logradouro,
    numero,
    complemento,
    bairro,
    cidade,
    estado,
    cep,
    perfil
) VALUES (
    'Fulano de Tal',
    'fulano@detal.com',
    'fulanodetal',
    '123456',
    'Rua de Cima',
    '123',
    'apto 10',
    'Centro',
    'Pirapora do Norte',
    'PI',
    '12345-678',
    'CLIENTE'
);

INSERT INTO usuarios (
    nome,
    email,
    nome_de_usuario,
    senha,
    logradouro,
    numero,
    complemento,
    bairro,
    cidade,
    estado,
    cep,
    perfil
) VALUES (
    'Beltrano da Silva',
    'beltrano@dasilva.com',
    'bsilva',
    '888888',
    'Avenida Brasil',
    '765',
    '',
    'Mirante do Alto',
    'Santeiros',
    'RS',
    '54321-001',
    'PROPRIETARIO'
);

