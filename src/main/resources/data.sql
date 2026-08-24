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

