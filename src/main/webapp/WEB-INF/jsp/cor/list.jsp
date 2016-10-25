                <div class="content-header">
                    <h1>
                        ${title}
                    </h1>
                </div>

                <!-- content -->
                <div class="content">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="box box-default">
                                <div class="box-header with-border">
                                    <h3 class="box-title">
                                        ${subTitle}
                                    </h3>
                                </div>
                                <!-- ./box-header -->

                                <div class="box-body">
                                    <a ui-sref="novaCor" class="btn-floating"><i class="fa fa-lg fa-plus"></i></a>
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
                                                    <a ui-sref="editarCor({id : cor.id})" role="button" title="${editTitle}"><i class="fa fa-fw fa-pencil"></i></a>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    <p ng-hide="cores.length > 0">Nenhuma cor cadastrada até o momento. Clique <a ui-sref="novaCor">aqui</a> para cadastrar.</p>
                                </div>
                                <!-- ./box-body -->
                            </div>
                            <!-- ./box -->

                            <!-- modalWindow -->
                            <div class="modal fade" id="modalWindow" tabindex="-1" role="dialog" aria-labelledby="modalWindowLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" tabindex="-1" data-dismiss="modal" aria-hidden="true">&times;</button>
                                            <h4 class="modal-title" id="modalWindowLabel"></h4>
                                        </div>
                                        <div class="modal-body"></div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" tabindex="-1" data-dismiss="modal">Fechar</button>
                                            <button type="button" class="btn btn-primary" tabindex="-1" id="saveIt">Salvar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- ./modalWindow -->
                        </div>
                        <!-- ./col-xs-12 -->
                    </div>
                    <!-- ./row -->
                </div>
                <!-- ./content -->