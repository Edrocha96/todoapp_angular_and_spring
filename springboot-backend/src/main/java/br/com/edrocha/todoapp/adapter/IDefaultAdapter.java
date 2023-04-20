package br.com.edrocha.todoapp.adapter;

import br.com.edrocha.todoapp.entity.Adaptable;

public interface IDefaultAdapter <I extends Adaptable, O extends Adaptable> {
	
	O adapt (I input);

}
