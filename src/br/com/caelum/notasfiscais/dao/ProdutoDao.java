package br.com.caelum.notasfiscais.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import br.com.caelum.notasfiscais.modelo.Produto;
import br.com.caelum.notasfiscais.util.JPAUtil;

public class ProdutoDao {

	public void adiciona(Produto produto) {
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();
		// persiste o objeto
		manager.persist(produto);
		manager.getTransaction().commit();
		manager.close();
	}

	public void remove(Produto produto) {
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();
		manager.remove(manager.merge(produto));
		manager.getTransaction().commit();
		manager.close();
	}

	public void atualiza(Produto produto) {
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();
		manager.merge(produto);
		manager.getTransaction().commit();
		manager.close();
	}

	public List<Produto> listaTodos() {
		EntityManager manager = new JPAUtil().getEntityManager();
		CriteriaQuery<Produto> query = manager.getCriteriaBuilder().createQuery(Produto.class);
		query.select(query.from(Produto.class));
		List<Produto> lista = manager.createQuery(query).getResultList();
		manager.close();
		return lista;
	}

	public Produto buscaPorId(Long id) {
		EntityManager manager = new JPAUtil().getEntityManager();
		Produto produto = manager.find(Produto.class, id);
		manager.close();
		return produto;

}}
