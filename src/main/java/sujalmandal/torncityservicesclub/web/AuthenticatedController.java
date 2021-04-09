package sujalmandal.torncityservicesclub.web;

import sujalmandal.torncityservicesclub.dtos.request.RequestDTO;
import sujalmandal.torncityservicesclub.web.filter.AuthenticatedUser;

public interface AuthenticatedController {
    public default void injectPlayerInRequest(RequestDTO request) {
	request.setPlayer(AuthenticatedUser.getPlayer());
    }
}
