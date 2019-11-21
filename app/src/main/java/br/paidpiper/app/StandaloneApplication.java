package br.paidpiper.app;

import org.springframework.context.annotation.Import;

import br.piedpiper.backend.BackendApplication;

@Import({ BackendApplication.class })
public class StandaloneApplication {
}
