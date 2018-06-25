-- inserir
INSERT INTO Alunos(nome, idade) VALUES(?,?);

-- atualizar 
UPDATE Alunos SET nome = ?, idade = ? WHERE id = ?;

-- deletar
DELETE FROM Alunos WHERE id = ?;

-- selecionar
SELECT id, nome, idade FROM Alunos;

-- selecionar com o nome como parametro
SELECT id, nome, idade FROM Alunos WHERE nome LIKE ?