CREATE TABLE historico_graduacoes(
    id BIGSERIAL PRIMARY KEY,
    matricula_modalidade_id BIGINT NOT NULL REFERENCES matriculas_modalidades(id),
    graduacao_id BIGINT NOT NULL REFERENCES graduacoes(id),
    data_alteracao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);