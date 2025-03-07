package br.com.branetlogistica.ms_transferencia.config;


public class QueueConstants {
	
	public static final String APP_NAME = "msentrega";
	
	public static final String EXCHANGE_CADASTRO = "CADASTRO";  
	public static final String EXCHANGE_REGISTRO = "REGISTRO"; 
	public static final String EXCHANGE_ENTREGA = "ENTREGA"; 
	
	
	public static final String KEY_UNIDADE = "CADASTRO.unidade";
	public static final String QUEUE_UNIDADE = KEY_UNIDADE+"."+APP_NAME;	
	
	public static final String KEY_CENTROCUSTO = "CADASTRO.centrocusto";
	public static final String QUEUE_CENTROCUSTO = KEY_CENTROCUSTO+"."+APP_NAME;
		
	public static final String KEY_ESTADO = "CADASTRO.estado";
	public static final String QUEUE_ESTADO = KEY_ESTADO+"."+APP_NAME;
	
	public static final String KEY_CIDADE = "CADASTRO.cidade";
	public static final String QUEUE_CIDADE = KEY_CIDADE+"."+APP_NAME;
	
	public static final String KEY_ENDERECO = "CADASTRO.endereco";
	public static final String QUEUE_ENDERECO =  KEY_ENDERECO+"."+APP_NAME;
		
	public static final String KEY_ESPECIALISTAEXTERNO = "CADASTRO.especialista_externo";
	public static final String QUEUE_ESPECIALISTAEXTERNO = KEY_ESPECIALISTAEXTERNO+"."+APP_NAME;
		
	public static final String KEY_PESSOAJURIDICA = "CADASTRO.pessoa_juridica";
	public static final String QUEUE_PESSOAJURIDICA = KEY_PESSOAJURIDICA+"."+APP_NAME;
		
	public static final String KEY_INDIVIDUO = "CADASTRO.individuo";
	public static final String QUEUE_INDIVIDUO = KEY_INDIVIDUO+"."+APP_NAME;
		
	public static final String KEY_USUARIO = "CADASTRO.usuario";
	public static final String QUEUE_USUARIO = KEY_USUARIO+"."+APP_NAME;
	
	public static final String KEY_MERCADORIA = "CADASTRO.mercadoria";
	public static final String QUEUE_MERCADORIA = KEY_MERCADORIA+"."+APP_NAME;
		
	public static final String KEY_LOTE = "CADASTRO.lote";
	public static final String QUEUE_LOTE = KEY_LOTE+"."+APP_NAME;
		
	public static final String KEY_CONFIGURACAOMERCADORIA = "CADASTRO.configuracao_mercadoria";
	public static final String QUEUE_CONFIGURACAOMERCADORIA = KEY_CONFIGURACAOMERCADORIA+"."+APP_NAME;
		
	public static final String KEY_ENTRADAESTOQUE = "REGISTRO.entrada";
	public static final String QUEUE_ENTRADAESTOQUE = KEY_ENTRADAESTOQUE+"."+APP_NAME;
		
	public static final String KEY_SAIDAESTOQUE = "REGISTRO.saida";
	public static final String QUEUE_SAIDAESTOQUE = KEY_SAIDAESTOQUE+"."+APP_NAME;
		
	public static final String KEY_POSICAOESTOQUE = "REGISTRO.posicao_estoque";
	public static final String QUEUE_POSICAOESTOQUE = KEY_POSICAOESTOQUE+"."+APP_NAME;
		
	public static final String KEY_DISPENSACAO = "REGISTRO.dispensacao";
	public static final String QUEUE_DISPENSACAO = KEY_DISPENSACAO+"."+APP_NAME;		
	
	public static final String KEY_PESSOAFISICA = "CADASTRO.pessoa_fisica";
	public static final String QUEUE_PESSOAFISICA = KEY_PESSOAFISICA+"."+APP_NAME;
			
	public static final String KEY_FUNCIONARIO = "CADASTRO.funcionario";
	public static final String QUEUE_FUNCIONARIO = KEY_FUNCIONARIO+"."+APP_NAME;
	
	public static final String KEY_VEICULO = "CADASTRO.veiculo";
	public static final String QUEUE_VEICULO = KEY_VEICULO+"."+APP_NAME;
	
	public static final String KEY_VEICULO_MARCA = "CADASTRO.veiculo_marca";
	public static final String QUEUE_VEICULO_MARCA = KEY_VEICULO_MARCA+"."+APP_NAME;
	
	public static final String KEY_VEICULO_MODELO = "CADASTRO.veiculo_modelo";
	public static final String QUEUE_VEICULO_MODELO = KEY_VEICULO_MODELO+"."+APP_NAME;
	
	public static final String KEY_ENTREGAITEM = "ENTREGA.entrega_item";
	public static final String QUEUE_ENTREGAITEM = KEY_ENTREGAITEM+"."+APP_NAME;	
	
	
 	
}
