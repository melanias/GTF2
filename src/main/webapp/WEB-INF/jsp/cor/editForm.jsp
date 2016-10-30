                    <section class="md-whiteframe-1dp">
                        <md-toolbar class="md-whiteframe-1dp">
                            <h1 class="md-toolbar-tools">
                                <span flex>{{subTitle}}</span>
                                <md-button class="md-icon-button" ui-sref="^.list">
                                    <md-tooltip md-direction="left">
                                        Voltar
                                    </md-tooltip>
                                    <md-icon>keyboard_arrow_left</md-icon>
                                </md-button>
                            </h1>
                        </md-toolbar>

                        <div layout-margin layout="column">
                            <form name="form" novalidate ng-submit="updateColor()">
                                <md-input-container>
                                    <input type="hidden" ng-model="cor.id">

                                    <label for="nome">Cor</label>
                                    <input ng-model="cor.nome" id="nome" name="nome" maxlength="50" required autocomplete="off">
                                </md-input-container>
                                <md-divider></md-divider>
                                <br>
                                <a ui-sref="^.list" class="md-button md-raised md-warn">Cancelar</a>
                                <md-button type="submit" class="md-raised md-accent" ng-disabled="form.$pristine || form.$invalid">Salvar</md-button>
                            </form>
                        </div>
                        <div class="alert alert-danger alert-dismissable" ng-show="errors.length > 0">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <p ng-repeat="error in errors"><strong>{{error.message}}</strong></p>
                        </div>
                    </section>