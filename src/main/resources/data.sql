-- SQL inicial para ambiente dev

CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    nome_de_usuario VARCHAR(255) UNIQUE,
    senha VARCHAR(255),
    logradouro VARCHAR(255),
    numero VARCHAR(255),
    complemento VARCHAR(255),
    bairro VARCHAR(255),
    cidade VARCHAR(255),
    estado VARCHAR(255),
    cep VARCHAR(255),
    perfis VARCHAR(255),
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
    perfis
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
    '[usuario]'
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
    perfis
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
    '[proprietario,usuario]'
);

