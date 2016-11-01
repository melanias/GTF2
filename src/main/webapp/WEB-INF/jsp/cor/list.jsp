                    <section class="md-whiteframe-1dp">
                        <md-toolbar class="md-whiteframe-1dp">
                            <h1 class="md-toolbar-tools">
                                <span flex>{{subTitle}}</span>
                                <md-button class="md-icon-button" ui-sref="^.add">
                                    <md-tooltip md-direction="left">
                                        Cadastrar cor
                                    </md-tooltip>
                                    <md-icon>add_circle_outline</md-icon>
                                </md-button>
                            </h1>
                        </md-toolbar>

                        <div layout-margin>
                            <div class="box box-default">
                                <div class="box-body">
                                    <a ui-sref="^.add" class="btn-floating"><i class="fa fa-lg fa-plus"></i></a>
                                    <table class="table table-hover table-bordered table-condensed" ng-show="cores.length > 0">
                                        <thead>
                                            <tr>
                                                <th style="width: 5%;">#</th>
                                                <th>Cor</th>
                                                <th style="width: 5%;"></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr ng-repeat="cor in cores">
                                                <td>{{$index+1}}</td>
                                                <td>{{cor.nome}}</td>
                                                <td>
                                                    <md-button class="md-icon-button" ui-sref="^.edit({id: cor.id})">
                                                        <md-tooltip md-direction="left">
                                                            Editar cor
                                                        </md-tooltip>
                                                        <!--<md-icon>mode_edit</md-icon>-->
                                                        <i class="fa fa-fw fa-pencil"></i>
                                                    </md-button>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    <p ng-hide="cores.length > 0">Nenhuma cor cadastrada até o momento. Clique <a ui-sref="^.add">aqui</a> para cadastrar.</p>
                                </div>
                                <!-- ./box-body -->
                            </div>
                            <!-- ./box -->
                        </div>
                    </section>