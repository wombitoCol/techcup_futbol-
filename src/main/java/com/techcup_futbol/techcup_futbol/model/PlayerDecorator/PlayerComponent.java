package com.techcup_futbol.techcup_futbol.PlayerDecorator;

import java.util.List;

public interface PlayerComponent {

    String getName();

    List<String> getActions();

    boolean canPerformAction(String action);
}