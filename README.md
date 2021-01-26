# dbserver

* Versão do Java 11.
* Banco de dados Postgres.

**Endpoints**

**Incluir Pauta (POST):** <br>
`http://localhost:8080/api/pauta/nova-pauta`<br>
Body exemplo: `{ "descricao": "Remover ilha do café" }`

**Visualizar Pauta(s) (GET):**<br>
`http://localhost:8080/api/pauta/obter-pauta`<br>
`http://localhost:8080/api/pauta/obter-pauta/{id}`<br>

**Abrir Sessão para Votação (POST):**<br>
`http://localhost:8080/api/sessao-votacao/abrir-sessao`<br>
Body exemplo:
`{
    "idPauta": 19,
    "duracaoEmMinuto": 5 (opcional)
}`

**Votar (POST):**<br>
`http://localhost:8080/api/voto/votar`<br>
Body exemplo:
`{
    "idPauta": 35,
    "cpfAssociado": "26756813005",
    "resposta": true
}`

**OBSERVAÇÕES** <br>
**Associado:** Não foi necessário um endpoint para o cadastro do Associado, no momento do voto é informado o CPF do associado, caso o CPF seja válidado pela API do Heroku o associado então será persistido no BD, e nas próximas vezes não será feita a consulta pelo Heroku, pois o associado existe na base. <br>
**Encerrar Sessão:** O encerramento de uma sessão é feito através de uma rotina que dispara a cada 1 minuto procurando por sessões abertar que extrapolaram o perído permitido para a votação, uma vez fechada a sessão não será possível novos votos e o resultado da votação poderá ser obtido pelo endpoint `/obter-pauta/{id}`. <br>
**Testes**: Não utilizei os testes unitários pois ainda não possuo conhecimento, não estou habituado a utiliza-los ainda, estou estudando o TDD, mas não me senti confortável para utiliza-lo ainda aqui.