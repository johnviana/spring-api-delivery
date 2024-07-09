package com.apiDelivery.api.domain.model.dto;

import com.apiDelivery.api.domain.model.EmailServer;

public class EmailServerDTO {

    private Integer codigo;
    private String protocolo;
    private String host;
    private int porta;
    private boolean tlsSslHabilitado;

    // Construtor padrão
    public EmailServerDTO() {
    }

    // Construtor para criação do DTO a partir de uma entidade EmailServer
    public EmailServerDTO(EmailServer emailServer) {
        this.codigo = emailServer.getCodigo();
        this.protocolo = emailServer.getProtocolo();
        this.host = emailServer.getHost();
        this.porta = emailServer.getPorta();
        this.tlsSslHabilitado = emailServer.getTlsSslHabilitado();
    }

    // Construtor para criação do DTO com parâmetros
    public EmailServerDTO(Integer codigo, String protocolo, String host, int porta, boolean tlsSslHabilitado) {
        this.codigo = codigo;
        this.protocolo = protocolo;
        this.host = host;
        this.porta = porta;
        this.tlsSslHabilitado = tlsSslHabilitado;
    }

    public void atualizarEntity(EmailServer emailServer) {
        emailServer.setProtocolo(this.protocolo);
        emailServer.setHost(this.host);
        emailServer.setPorta(this.porta);
        emailServer.setTlsSslHabilitado(this.tlsSslHabilitado);
    }

    // Método para converter DTO para a entidade EmailServer
    public EmailServer toEntity() {
        EmailServer emailServer = new EmailServer();
        emailServer.setCodigo(this.codigo);
        emailServer.setProtocolo(this.protocolo);
        emailServer.setHost(this.host);
        emailServer.setPorta(this.porta);
        emailServer.setTlsSslHabilitado(this.tlsSslHabilitado);
        return emailServer;
    }

    // Método para atualizar o DTO a partir de outro DTO
    public void atualizarDTO(EmailServerDTO emailServerDTO) {
        this.protocolo = emailServerDTO.getProtocolo();
        this.host = emailServerDTO.getHost();
        this.porta = emailServerDTO.getPorta();
        this.tlsSslHabilitado = emailServerDTO.isTlsSslHabilitado();
    }
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPorta() {
        return porta;
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }

    public boolean isTlsSslHabilitado() {
        return tlsSslHabilitado;
    }

    public void setTlsSslHabilitado(boolean tlsSslHabilitado) {
        this.tlsSslHabilitado = tlsSslHabilitado;
    }
}
