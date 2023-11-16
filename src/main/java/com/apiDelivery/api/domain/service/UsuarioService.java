package com.apiDelivery.api.domain.service;

import com.apiDelivery.api.domain.exception.EntidadeNaoEncontradaExcepetion;
import com.apiDelivery.api.domain.exception.NegocioException;
import com.apiDelivery.api.domain.model.Grupo;
import com.apiDelivery.api.domain.model.Usuario;
import com.apiDelivery.api.domain.repository.UsuarioRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UsuarioService implements UserDetailsService {

	private Logger logger = Logger.getLogger(UsuarioService.class.getName());

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private GrupoService grupoService;

	@Autowired
	private EntityManager entityManager;

	public List<Usuario> listarTodos() {
		return usuarioRepository.findAll();
	}

	@Transactional
	public Usuario salvar(Usuario usuario) {

		entityManager.detach(usuario);

		Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());

		if(usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			throw new NegocioException(
					String.format("Já existe um email cadastrado no banco de dados %s", usuario.getEmail()));
		}

		return usuarioRepository.save(usuario);
	}

	@Transactional
	public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
		Usuario usuario = buscarOuFalhar(usuarioId);

		if (usuario.senhaNaoCoincideCom(senhaAtual)) {
			throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
		}

		usuario.setSenha(novaSenha);
	}

	public Usuario buscarOuFalhar(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new EntidadeNaoEncontradaExcepetion(
					String.format("Usuario não encontrado", usuarioId)));
    }

	@Transactional
	public void desassociar(Long usuarioId, Long grupoId) {
		Usuario usuario = buscarOuFalhar(usuarioId);
		Grupo grupo = grupoService.buscarOuFalhar(grupoId);

		usuario.removerGrupo(grupo);
	}

	@Transactional
	public void associar(Long usuarioId, Long grupoId) {
		Usuario usuario = buscarOuFalhar(usuarioId);
		Grupo grupo = grupoService.buscarOuFalhar(grupoId);

		usuario.adicionarGrupo(grupo);
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("FINDING ONE USER BY NAME" + username + "!");
		var user = usuarioRepository.findBynome(username);
		if (user != null) {
			return user;
		} else {
			throw new UsernameNotFoundException("Us	erName " + username + "not found!");
		}

	}
}
