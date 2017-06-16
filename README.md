# desafio1_machineProj
Desafio1 enviado para Lenovo

1- Passos para rodar o projeto
1.1 ter instalado o JAVA 8 e o JRE em sua máquina
1.2 ter configurado o JAVA 8 e o JRE em sua máquina https://www.java.com/pt_BR/download/help/download_options.xml
1.3 ter um servidor como o TomCat instalado http://tomcat.apache.org/
1.4 se for de sua preferência poderá rodá-lo no ambiente de desenvolvimento para isso será necessário instalar uma IDE como Netbeans.
1.5 ao instalar e configurar a IDE você deverá baixar o projeto do github e abrí-lo em sua Ide 
1.6 após ter aberto o projeto será necessário fazer o procedimento de build, para o Maven(repositório de bibliotecas e automatizador de tarefas) baixar as dependencias do projeto.
1.7 após a build concluida, você poderá rodar o programa

2 - Cobertura do desafio
2.1 simule algumas respostas de uma máquina real: baseado no exemplo enviado as respostas são obtidas por meio dos verbos HTTP GET e POST
2.2 Esta aplicação deverá ser acessível via interface HTTP REST (não precisa de frontend): os serviços estão disponíveis pelas urls:
    http://localhost:9001/getmachines  -->GET
    http://localhost:9001/getmachine?id=1 -->GET
    http://localhost:9001/add -->POST
              //Payload example:
                //{
                //"id": 1,
                //"model": "teste1",
                //"serialNumber": "asd1",
                //"name": "nome",
                //"processor": "i7",
                //"memory": "8g",
                //"hd": "1tb",
                //"temperature": "23",
                //"powerStatus": 0,
                // "address":[0,0,0,0] 
                //}
    http://localhost:9001/edit -->POST
              //Payload example:
                //{
                //"id": 1,
                //"model": "teste2",
                //"serialNumber": "asd2",
                //"name": "nome 2",
                //"processor": "i7",
                //"memory": "8g",
                //"hd": "1tb"
                //}
    http://localhost:9001/power?id=1&togglePower=1 --> machine on -->GET
    http://localhost:9001/power?id=1&togglePower=0 --machine off -->GET
    http://localhost:9001/connect -->POST
              //Payload example:
                //{"user":"user",
                //"password":"password",
                //[127,0,0,2]
                //}
    http://localhost:9001/disconnect -->POST
              //Payload example:
                //[127,0,0,2]
    http://localhost:9001/exclude?id=1 -->GET
                
 2.3 com autenticação: A autenticação está implementada e configurada para ser requistada a conexão com a máquina, para dar a impressão de que o usuário esta conctando em uma máquina virtual realmente
 2.4 retornar informações básicas, como modelo, tipo de processador, memória, tamanho do disco: Informações Estão disponibilizadas pelos métodos: 
 getMachines e getMachine
 2.4 também deverá aceitar comandos básicos, como ligar e desligar a máquina: Estão disponoveis os comandos
 ligar: metodo powerMachine
 desligar: metodo powerMachine
 conectar: connectToMachine
 desconectar: disconnectToMachine
2.5 Seu código deve ser flexível o suficiente para permitir rodar diversas instâncias simultaneamente – afinal, queremos simular várias máquinas ao mesmo tempo :-) A maneira como você fará seu código para atingir este objetivo fica à sua escolha. 

Para conseguir este cobrir este requisito, trabalhei no conceito de Pool de máquinas, que é uma lista de máquinas onde não é permitido a inserção de máquinas repetidas , esta lista pode ser visualizada ao mesmo tempo por vários usuários os quais podem exercer algumas funcionalidades sobre ela:
incluir máquina: createMachine
editar máquina: editMachine
excluir máquina: excludeMachine
listar máquinas: getMachines
filtrar máquina por seu id: getMachine
 
 
