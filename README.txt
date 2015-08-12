INSTRUÇÕES PARA EXECUÇÃO

O sistema em questão foi desenvolvido para executar em um servidor web, preferencialmente Tomcat 7.x.
 
O arquivo WAR deverá ser colocado na pasta de deploy do servidor (apache-tomcat-7.0.55\webapps\.).

O banco de dados deverá ser previamente criado com a descrição 'weblog'. 
As tabelas serão geradas e configuradas automaticamente quando o sistema estiver disponivel.

----------------------------------------------------------------------------------
----------------------------------------------------------------------------------

DAS SOLUÇÕES ESCOLHIDAS 

Para o desenvolvimento dos web-services, foi utilizando o framework Jersey por se tratar de uma implementação conhecida e comum no mercado.
Para consumir os serviços o link utilizado para acessar o wadl é: http://localhost:8080/WebLogCIET/ws/application.wadl

Para persistencia e mapeamento objeto relacional, foi utilizado o hibernate com anotações, reduzindo assim a quantidade de arquivos de configuração
e agilizar a execução, dado o período disponível para desenvolvimento. 

Apesar de não ser a alternativa adequada para este problema, o banco de dados utilizado foi o mysql. Para a resolução desse problema, o ideal
seria utilizar o banco de dados não relacional Neo4J. Por ser uma base especializada em tratamento de grafos, em um ambiente real de desenvolvimento
sua escolha seria ideal, porém, sua configuração é mais custosa e a chance de problemas na validação do exercício subiria. 

O sistema também não está desenvolvido de maneira escalar e com performance ótima pela limitação de tempo definida para resolução do problema. 
Ainda assim tomei a cautela de criar uma classe singleton para manter resultados em cache e evitar acessos repetidos ao banco para buscas 
semelhantes. Isso ajuda a diminuir overhead no sistema, mas não impede caso o sistema esteja sob grande quantidade de acessos.

O framework spring poderia ser utilizado em vários pontos do sistema, mas em virtude do tempo disponível, optei por abrir mão dessa tecnologia.
Da mesma maneira, na configuração do hibernate mantive o Fetch como EAGER para diminuir a necessidade de tratamento da excessão LazyInicializationException.
O uso do generics também seria um ponto a ser aplicado, para aumentar a abstração e modularidade do sistema.

Apesar de não ser a melhor alternativa, as transações foram todas deixadas para responsabilidade da camada DAO para simplificar o codigo.
O melhor porém seria manter uma classe de pool de sessões que seria invocada de acordo com cada operação.

A validação dos metodos poderia ser feita por uma camada a parte, mas foi incluida de forma simplificada para agilizar o desenvolvimento.

----------------------------------------------------------------------------------
----------------------------------------------------------------------------------

MODELAGEM ESCOLHIDA

    ________ *    * _____________ *       1 ________ 1        n ____________
   |  Mapa  |----->| Mapa_Aresta |<--------| Aresta |----------|  Vertices  | 

----------------------------------------------------------------------------------
----------------------------------------------------------------------------------

METODOS WEB-SERVICE

Foram desenvolvidos três metodos em web-service e disponibilizados com as seguintes funcionalidades:

             -------------------------------------------

- gravarMapa
Este metodo registra um novo mapa e recebe uma lista de arestas. Os atributos esperados são:
nome=<STRING nome do mapa>
&item=<STRING vertice_1> <STIRNG vertice_2> <INTEIRO distancia>  
O campo item pode ser repetido de acordo com a necessidade.
Os três campos devem estar separados por um espaço simples

Ex.:
http://localhost:8080/WebLogCIET/ws/gravarMapa?nome=belo horizonte&item=A B 10&item=B D 15&item=A C 20&item=C D 30&item=B E 50&item=D E 30


             -------------------------------------------

- buscarMelhorCaminho
Metodo que busca a solução para o problema, obtendo o caminho de menor custo.
origem=<STRING vertice de partida>
&destino=<STRING vertice de chegada>
&autonomia=<INTEGER rendimento do veiculo em kilometros em relação a um litro de combustivel>
&valorLitro=<DOUBLE valor referente a um litro de combustível>
Ex.:
http://localhost:8080/WebLogCIET/ws/buscarMelhorCaminho?origem=A&destino=D&autonomia=10&valorLitro=2.50

---------------------------------------------------

- removerMapa
Metodo para remoção de um determinado mapa pelo seu nome. 
A execução desse comando excluirá todos as arestas e vertices relacionados a ele.
nome=<STRING nome do mapa a ser excluido>
Ex.:
http://localhost:8080/WebLogCIET/ws/removerMapa?nome=belo horizonte
