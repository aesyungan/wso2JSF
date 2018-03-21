/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayungan.bean;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.wso2.carbon.identity.sso.agent.SSOAgentConstants;
import org.wso2.carbon.identity.sso.agent.bean.LoggedInSessionBean;
import org.wso2.carbon.identity.sso.agent.bean.SSOAgentConfig;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import org.wso2.carbon.identity.sso.agent.bean.LoggedInSessionBean;
import org.wso2.carbon.identity.sso.agent.bean.SSOAgentConfig;
import org.wso2.carbon.identity.sso.agent.SSOAgentConstants;

/**
 *
 * @author Alex
 */
@ManagedBean
@ViewScoped
public class indexController {

    String nameUser = "";

    public indexController() throws IOException {

//        System.out.println("Hola");
//        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//        nameUser = "";
//        nameUser += request.getRemoteUser();
        verificarLogueo();
        System.out.println("termino constructor ..");
    }

    public void verificarLogueo() throws IOException {

        String claimedId = null;
        String subjectId = null;
        Map<String, List<String>> openIdAttributes = null;
        Map<String, String> saml2SSOAttributes = null;
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (request.getSession(false) != null
                && request.getSession(false).getAttribute(SSOAgentConstants.SESSION_BEAN_NAME) == null) {
            request.getSession().invalidate();
            //login no valido va a index
            IrAPagina("index.xhtml");
            System.out.println("redirect");
            // return;

        }

        HttpSession session = request.getSession(true);
        //session.invalidate();
        SSOAgentConfig ssoAgentConfig = (SSOAgentConfig) request.getServletContext().getAttribute(SSOAgentConstants.CONFIG_BEAN_NAME);
        LoggedInSessionBean sessionBean = (LoggedInSessionBean) session.getAttribute(SSOAgentConstants.SESSION_BEAN_NAME);
        LoggedInSessionBean.AccessTokenResponseBean accessTokenResponseBean = null;

        if (sessionBean != null) {
            if (sessionBean.getOpenId() != null) {
                claimedId = sessionBean.getOpenId().getClaimedId();
                openIdAttributes = sessionBean.getOpenId().getSubjectAttributes();
            } else if (sessionBean.getSAML2SSO() != null) {
                subjectId = sessionBean.getSAML2SSO().getSubjectId();
                //access correct optenemos el nombre de usuario
                nameUser = subjectId;
                System.out.println("correcto usuario:" + nameUser);
                saml2SSOAttributes = sessionBean.getSAML2SSO().getSubjectAttributes();
                accessTokenResponseBean = sessionBean.getSAML2SSO().getAccessTokenResponseBean();
            } else {
                //no logeado correctamente va a index 
                IrAPagina("index.xhtml");
                System.out.println("redirect");
//                return;
            }
        } else {
            //no logeado correctamente va a index
            IrAPagina("index.xhtml");
            System.out.println("redirect");
//            return;
        }

    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }
//
//    public void logOutHTTPRedirect() throws IOException {
//        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//        HttpSession session = request.getSession();
//        //session.invalidate();
//        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//        //close session en cas server
//        externalContext.redirect("logout?SAML2.HTTPBinding=HTTP-Redirect");
//    }
//
//    public void logOutHTTPPost() throws IOException {
//        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//        HttpSession session = request.getSession();
//        //session.invalidate();
//        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//        //close session en cas server
//        externalContext.redirect("logout?SAML2.HTTPBinding=HTTP-POST");
//    }

    public void IrAPagina(String url) throws IOException {
        System.out.println("Redirect to page");
        FacesContext.getCurrentInstance().getExternalContext().redirect(url);
    }

}
