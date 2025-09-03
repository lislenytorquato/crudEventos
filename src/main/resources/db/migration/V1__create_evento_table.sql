CREATE TABLE evento(
 id BIGINT PRIMARY KEY,
 nome VARCHAR(255),
 descricao VARCHAR(255),
 data DATE,
 local_id BIGINT,
 FOREIGN KEY (local_id) REFERENCES local(id)
);