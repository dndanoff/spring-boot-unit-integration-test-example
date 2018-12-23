package com.danoff.demo.event;

import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;

public class CommandReceived<T> implements ResolvableTypeProvider {
	
	private final T command;

	public CommandReceived(T command) {
		this.command = command;
	}

	public final T getCommand() {
		return command;
	}

	@Override
	public ResolvableType getResolvableType() {
		return ResolvableType.forClassWithGenerics(getClass(), ResolvableType.forInstance(command));
	}
}
