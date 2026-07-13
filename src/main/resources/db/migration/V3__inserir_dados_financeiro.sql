-- Alunos necessários para as matrículas abaixo (a tabela alunos ainda está vazia neste ponto)
INSERT INTO alunos (nome, data_nascimento, sexo, telefone, celular, email, cidade, estado) VALUES
('João Pedro Alves', '1997-01-20', 'M', '4833220000', '48991110000', 'joao.alves@email.com', 'Criciúma', 'SC'),
('Carlos Eduardo Silva', '1998-04-12', 'M', '4833221100', '48991112222', 'carlos.silva@email.com', 'Criciúma', 'SC'),
('Fernanda Oliveira Souza', '1995-09-30', 'F', '4833224455', '48991223344', 'fernanda.souza@email.com', 'Criciúma', 'SC');

-- Musculação não tem graduações em V2 (faz sentido, não é uma arte marcial),
-- mas a coluna graduacoes_id em matriculas_modalidades é NOT NULL, então criamos um nível único genérico.
INSERT INTO graduacoes (modalidade_id, nome)
SELECT id, 'Nível Único' FROM modalidades WHERE nome = 'Musculação';

INSERT INTO matriculas (aluno_id, data_matricula, data_vencimento, status)
VALUES(2, CURRENT_DATE - INTERVAL '90 days', 10, 'ATIVA');

INSERT INTO matriculas (aluno_id, data_matricula, data_vencimento, status)
VALUES(3, CURRENT_DATE - INTERVAL '60 days', 15, 'ATIVA');

INSERT INTO matriculas_modalidades(
    matricula_id,
    modalidade_id,
    graduacoes_id,
    planos_id,
    data_inicio
)
SELECT
    m.id,
    mo.id,
    g.id,
    p.id,
    CURRENT_DATE - INTERVAL '90 days'
FROM matriculas m
    JOIN modalidades mo ON mo.nome = 'Musculação'
    JOIN planos p ON p.modalidade_id = mo.id AND p.nome = 'Plano Mensal'
    JOIN graduacoes g ON g.modalidade_id = mo.id AND g.nome = 'Nível Único'
WHERE m.aluno_id IN (2);

INSERT INTO matriculas_modalidades(
    matricula_id,
    modalidade_id,
    graduacoes_id,
    planos_id,
    data_inicio
)
SELECT
    m.id,
    mo.id,
    g.id,
    p.id,
    CURRENT_DATE - INTERVAL '60 days'
FROM matriculas m
    JOIN modalidades mo ON mo.nome = 'Jiu-Jitsu'
    JOIN graduacoes g ON g.modalidade_id = mo.id AND g.nome = 'Faixa Branca'
    JOIN planos p ON p.modalidade_id = mo.id AND p.nome = 'Plano Mensal'
WHERE m.aluno_id IN (3);


INSERT INTO faturas_matriculas(
    matricula_id,
    data_vencimento,
    valor,
    data_pagamento,
    status
)
SELECT
    m.id,
    CURRENT_DATE - INTERVAL '60 days',
    120.00,
    CURRENT_TIMESTAMP - INTERVAL '58 days',
    'PAGA'
FROM matriculas m
WHERE m.aluno_id IN (2);


INSERT INTO faturas_matriculas(
    matricula_id,
    data_vencimento,
    valor,
    data_pagamento,
    status
)
SELECT
    m.id,
    CURRENT_DATE - INTERVAL '30 days',
    120.00,
    CURRENT_TIMESTAMP - INTERVAL '29 days',
    'PAGA'
FROM matriculas m
WHERE m.aluno_id IN (2);


INSERT INTO faturas_matriculas(
    matricula_id,
    data_vencimento,
    valor,
    status
)
SELECT
    m.id,
    CURRENT_DATE - INTERVAL '10 days',
    120.00,
    'ABERTA'
FROM matriculas m
WHERE m.aluno_id IN (2);


INSERT INTO faturas_matriculas(
    matricula_id,
    data_vencimento,
    valor,
    data_pagamento,
    status
)
SELECT
    m.id,
    CURRENT_DATE - INTERVAL '30 days',
    180.00,
    CURRENT_TIMESTAMP - INTERVAL '28 days',
    'PAGA'
FROM matriculas m
WHERE m.aluno_id IN (3);

INSERT INTO faturas_matriculas(
    matricula_id,
    data_vencimento,
    valor,
    status
)
SELECT
    m.id,
    CURRENT_DATE - INTERVAL '15 days',
    120.00,
    'ABERTA'
FROM matriculas m
WHERE m.aluno_id IN (3);