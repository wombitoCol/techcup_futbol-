package com.techcup_futbol.techcup_futbol.model.PlayerDecorator;

import java.util.List;

public interface PlayerComponent {

    String getName();

    List<String> getActions();

    boolean canPerformAction(String action);
}