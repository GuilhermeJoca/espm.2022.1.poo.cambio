package br.espm.cambio;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
* Esse e o microservico de moeda
*/ 


@Component
public class MoedaService {

    @Autowired
    MoedaRepository moedaRepository;

    public List<Moeda> listaAll(){

        return StreamSupport
            //Transforma de iteravel para lista
            .stream(moedaRepository.findAll().spliterator(), false)
            .collect(Collectors.toList())
            //Transforma de Model para Objeto
            .stream().map(MoedaModel::to)
            .collect(Collectors.toList());
        
    }
    
    public Moeda create(Moeda vo){
        vo.setId(UUID.randomUUID());
        return moedaRepository.save(new MoedaModel(vo)).to();

    }

    public Moeda findBySimbolo(String simbolo){
        return moedaRepository.findBySimbolo(simbolo)
                        .map(MoedaModel::to)
                        .orElse(null);

    }

    public Moeda findById(String id) {
        return moedaRepository.findById(id).map(MoedaModel::to).orElse(null);
    }
}
