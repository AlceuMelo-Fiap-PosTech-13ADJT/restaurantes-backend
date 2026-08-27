-- SQL inicial para ambiente dev

INSERT IGNORE INTO usuarios (
    nome,
    email,
    login,
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
    '$2a$12$aKzkwGUu98slUhJGqtnNbuYCo6Ymxtql1NQE1XX0fbAC8XNl6nzVm', -- 123Abc!
    'Rua de Cima',
    '123',
    'apto 10',
    'Centro',
    'Pirapora do Norte',
    'PI',
    '12345-678',
    'CLIENTE'
);

INSERT IGNORE INTO usuarios (
    nome,
    email,
    login,
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
    '$2a$12$98G.k.adZfYHAO/IS3DLQu5H5vLcJDRt2U7aOUPA3i9LRPPjXoYXK', -- Xyz#987
    'Avenida Brasil',
    '765',
    '',
    'Mirante do Alto',
    'Santeiros',
    'RS',
    '54321-001',
    'PROPRIETARIO'
);

