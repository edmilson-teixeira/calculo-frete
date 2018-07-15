Para executar a aplicação: <br/>
1 - Executar o script "mvn-install.bat" para compilar e empacotar em um JAR; <br/>
2 - Executar o script "java-run.bat" para executar a aplicação; <br/>
3 - E finalmente acessar a aplicação pela URL http://localhost:8080/.<br/>
<br />
Sobre o exercício 2:<br />
1 - Os comentários como estão, são dispensáveis;<br />
2 - A regra de negócio pode ser dividida em 2 funções bem definidas: uma pra cuidar da mensagem inicial e outra pra concatenar os códigos;<br />
3 - A variável global "texto" é dispensável, utilizando retorno nas funções; <br/> 
4 - É possível utilizar "String.format" para evitar duplicação de textos; <br/>
5 - Variáveis com nomes pouco exclarecedores, podem ser renomeadas para ter melhor sentido (cod, c, s); <br/>
6 - Formatar o código fazendo identação e adicionando chaves em todos os blocos de comandos, com início na mesma linha; <br/>
7 - Adicionar tipagem na lista de códigos, melhorando a leitura de desenvolvedores para saber qual é o tipo de 'codigo';