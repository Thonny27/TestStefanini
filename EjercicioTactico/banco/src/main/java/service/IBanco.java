package service;

import java.util.List;

import dao.BancoDao;

public interface IBanco {

	BancoDao addBanco(BancoDao banco);

	List<BancoDao> getBanco();

	BancoDao updateBanco(BancoDao banco);

	BancoDao deleteBanco(BancoDao banco);
}