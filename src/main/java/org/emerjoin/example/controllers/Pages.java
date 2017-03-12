package org.emerjoin.example.controllers;

import mz.co.hi.web.mvc.Controller;
import mz.co.hi.web.mvc.exceptions.MvcException;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author Mário Júnior
 */
@ApplicationScoped
public class Pages extends Controller {


    public void example() throws MvcException{

        this.callView();

    }

}
