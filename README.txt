INSTRU��ES PARA EXECU��O

O sistema em quest�o foi desenvolvido para executar em um servidor web, preferencialmente Tomcat 7.x.
 
O arquivo WAR dever� ser colocado na pasta de deploy do servidor (apache-tomcat-7.0.55\webapps\.).

O banco de dados dever� ser previamente criado com a descri��o 'weblog'. 
As tabelas ser�o geradas e configuradas automaticamente quando o sistema estiver disponivel.

----------------------------------------------------------------------------------
----------------------------------------------------------------------------------

DAS SOLU��ES ESCOLHIDAS 

Para o desenvolvimento dos web-services, foi utilizando o framework Jersey por se tratar de uma implementa��o conhecida e comum no mercado.
Para consumir os servi�os o link utilizado para acessar o wadl �: http://localhost:8080/WebLogCIET/ws/application.wadl

Para persistencia e mapeamento objeto relacional, foi utilizado o hibernate com anota��es, reduzindo assim a quantidade de arquivos de configura��o
e agilizar a execu��o, dado o per�odo dispon�vel para desenvolvimento. 

Apesar de n�o ser a alternativa adequada para este problema, o banco de dados utilizado foi o mysql. Para a resolu��o desse problema, o ideal
seria utilizar o banco de dados n�o relacional Neo4J. Por ser uma base especializada em tratamento de grafos, em um ambiente real de desenvolvimento
sua escolha seria ideal, por�m, sua configura��o � mais custosa e a chance de problemas na valida��o do exerc�cio subiria. 

O sistema tamb�m n�o est� desenvolvido de maneira escalar e com performance �tima pela limita��o de tempo definida para resolu��o do problema. 
Ainda assim tomei a cautela de criar uma classe singleton para manter resultados em cache e evitar acessos repetidos ao banco para buscas 
semelhantes. Isso ajuda a diminuir overhead no sistema, mas n�o impede caso o sistema esteja sob grande quantidade de acessos.

O framework spring poderia ser utilizado em v�rios pontos do sistema, mas em virtude do tempo dispon�vel, optei por abrir m�o dessa tecnologia.
Da mesma maneira, na configura��o do hibernate mantive o Fetch como EAGER para diminuir a necessidade de tratamento da excess�o LazyInicializationException.
O uso do generics tamb�m seria um ponto a ser aplicado, para aumentar a abstra��o e modularidade do sistema.

Apesar de n�o ser a melhor alternativa, as transa��es foram todas deixadas para responsabilidade da camada DAO para simplificar o codigo.
O melhor por�m seria manter uma classe de pool de sess�es que seria invocada de acordo com cada opera��o.

A valida��o dos metodos poderia ser feita por uma camada a parte, mas foi incluida de forma simplificada para agilizar o desenvolvimento.

----------------------------------------------------------------------------------
----------------------------------------------------------------------------------

MODELAGEM ESCOLHIDA

    ________ *    * _____________ *       1 ________ 1        n ____________
   |  Mapa  |----->| Mapa_Aresta |<--------| Aresta |----------|  Vertices  | 

----------------------------------------------------------------------------------
----------------------------------------------------------------------------------

METODOS WEB-SERVICE

Foram desenvolvidos tr�s metodos em web-service e disponibilizados com as seguintes funcionalidades:

             -------------------------------------------

- gravarMapa
Este metodo registra um novo mapa e recebe uma lista de arestas. Os atributos esperados s�o:
nome=<STRING nome do mapa>
&item=<STRING vertice_1> <STIRNG vertice_2> <INTEIRO distancia>  
O campo item pode ser repetido de acordo com a necessidade.
Os tr�s campos devem estar separados por um espa�o simples

Ex.:
http://localhost:8080/WebLogCIET/ws/gravarMapa?nome=belo horizonte&item=A B 10&item=B D 15&item=A C 20&item=C D 30&item=B E 50&item=D E 30


             -------------------------------------------

- buscarMelhorCaminho
Metodo que busca a solu��o para o problema, obtendo o caminho de menor custo.
origem=<STRING vertice de partida>
&destino=<STRING vertice de chegada>
&autonomia=<INTEGER rendimento do veiculo em kilometros em rela��o a um litro de combustivel>
&valorLitro=<DOUBLE valor referente a um litro de combust�vel>
Ex.:
http://localhost:8080/WebLogCIET/ws/buscarMelhorCaminho?origem=A&destino=D&autonomia=10&valorLitro=2.50

---------------------------------------------------

- removerMapa
Metodo para remo��o de um determinado mapa pelo seu nome. 
A execu��o desse comando excluir� todos as arestas e vertices relacionados a ele.
nome=<STRING nome do mapa a ser excluido>
Ex.:
http://localhost:8080/WebLogCIET/ws/removerMapa?nome=belo horizonte
